package com.example.obliviate

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color.parseColor
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


/**
 * Implementation of App Widget functionality.
 */

class NewAppWidget : AppWidgetProvider() {
    private val ACTION_SCHEDULED_UPDATE = "com.example.obliviate.SCHEDULED_UPDATE"

    //위젯이 설치될 때마다 호출되는 함수
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
        _scheduleNextUpdate(context)
    }

    private fun _scheduleNextUpdate(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // Substitute AppWidget for whatever you named your AppWidgetProvider subclass
        val intent = Intent(context, NewAppWidget::class.java)
        intent.action = ACTION_SCHEDULED_UPDATE
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        // Get a calendar instance for midnight tomorrow.
        val midnight = Calendar.getInstance()
        midnight[Calendar.HOUR_OF_DAY] = 0
        midnight[Calendar.MINUTE] = 0
        // Schedule one second after midnight, to be sure we are in the right day next time this
        // method is called.  Otherwise, we risk calling onUpdate multiple times within a few
        // milliseconds
        midnight[Calendar.SECOND] = 1
        midnight[Calendar.MILLISECOND] = 0
        midnight.add(Calendar.DAY_OF_YEAR, 1)

        // setExact ensures the intent goes off exactly at midnight.
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, midnight.timeInMillis, pendingIntent)
    }

    //위젯이 최초로 설치되는 순간 호출되는 함수
    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        //Toast.makeText(context, "onEnabled", Toast.LENGTH_SHORT).show()
    }

    //위젯이 마지막으로 제거되는 순간 호출되는 함수
    override fun onDisabled(context: Context) {
        //Toast.makeText(context, "onDisabled", Toast.LENGTH_SHORT).show()
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals(ACTION_SCHEDULED_UPDATE)) {
            val manager = AppWidgetManager.getInstance(context)
            val ids = manager.getAppWidgetIds(ComponentName(context!!, NewAppWidget::class.java))
            onUpdate(context!!, manager, ids)
        }

        super.onReceive(context, intent)
        val backgroundColor = intent?.getStringExtra("backgroundColor")
        val textColor = intent?.getStringExtra("textColor")
        if(textColor != null && backgroundColor != null){
            val parseBackgroundColor = parseColor(backgroundColor.toString())
            val parseTextColor = parseColor(textColor)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val remoteViews = RemoteViews(context?.packageName, R.layout.new_app_widget)
            val componentName = ComponentName(context!!, NewAppWidget::class.java)
            remoteViews.setInt(R.id.appwidget_layout, "setBackgroundColor", parseBackgroundColor)
            remoteViews.setTextColor(R.id.appwidget_text, parseTextColor)
            Toast.makeText(context, "위젯 설정 완료", Toast.LENGTH_SHORT).show()
            appWidgetManager.updateAppWidget(componentName, remoteViews)
        }
    }
}

//위젯의 크기 및 옵션이 변경될 때마다 호출되는 함수
internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    var db = FirebaseFirestore.getInstance()
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)

    db.collection("Text")
        .get()
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val today : String? = getTodayNum()
                for (document in task.result!!) {
                    if(document.data["idx"].toString() == today){
                        views.setTextViewText(R.id.appwidget_text, unescape(document.data["string"].toString()))
                        appWidgetManager.updateAppWidget(appWidgetId, views)
                        break
                    }
                }
            } else {
                Log.d("error", "Error getting documents.", task.exception)
            }
        }
}



private fun unescape(description: String): String? {
    return description.replace("\\\\n".toRegex(), "\\\n")
}

private fun getTodayNum() : String? {
    var cal = Calendar.getInstance()
    var day = cal.get(Calendar.DAY_OF_YEAR).toString()
    return day
}