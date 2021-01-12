package com.example.obliviate

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.RemoteViews
import android.widget.Toast
import com.example.obliviate.R.id.main_textview
import com.example.obliviate.databinding.CustomDialogBinding

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {

    private lateinit var binding: CustomDialogBinding
    //위젯이 설치될 때마다 호출되는 함수
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    //위젯이 최초로 설치되는 순간 호출되는 함수
    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        Toast.makeText(context, "onEnabled", Toast.LENGTH_SHORT).show()
    }

    //위젯이 마지막으로 제거되는 순간 호출되는 함수
    override fun onDisabled(context: Context) {
        Toast.makeText(context, "onDisabled", Toast.LENGTH_SHORT).show()
        // Enter relevant functionality for when the last widget is disabled
    }
}

//위젯의 크기 및 옵션이 변경될 때마다 호출되는 함수
internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
//    views.setTextViewText(main_textview, widgetText)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}