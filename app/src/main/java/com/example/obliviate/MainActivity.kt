package com.example.obliviate

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.graphics.Color.parseColor
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.obliviate.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Integer.parseInt
import java.util.*


class MainActivity : AppCompatActivity(), MyCustomDialogInterface {
    var db = FirebaseFirestore.getInstance()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        db.collection("Text")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val today : String? = getTodayNum()
                    for (document in task.result!!) {
                        if(document.data["idx"].toString() == today){
                            binding.mainTextview.text = unescape(document.data["string"].toString())
                            break
                        }
                    }
                } else {
                    Log.d("error", "Error getting documents.", task.exception)
                }
            }

    }

    fun onDialogBtnClicked(view: View){
        val myCustomDialog = MyCustomDialog(this, this, binding.mainTextview.text.toString())
        myCustomDialog.show()
    }

    private fun unescape(description: String): String? {
        return description.replace("\\\\n".toRegex(), "\\\n")
    }

    private fun getTodayNum() : String? {
        var cal = Calendar.getInstance()
        var day = cal.get(Calendar.DAY_OF_YEAR).toString()
        return day
    }

    override fun onRegisterBtnClicked(backgroundColor: String, textColor:String) {
        val widgetIntent = Intent(this, NewAppWidget::class.java)
        widgetIntent.putExtra("backgroundColor", backgroundColor)
        widgetIntent.putExtra("textColor", textColor)
        widgetIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
        this.sendBroadcast(widgetIntent) // 브로드캐스팅

//        val appWidgetId = intent?.extras?.getInt(
//                AppWidgetManager.EXTRA_APPWIDGET_ID,
//                AppWidgetManager.INVALID_APPWIDGET_ID
//        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID
//
//        val views = RemoteViews(this.packageName, R.layout.new_app_widget)
//        views.setTextColor(R.id.appwidget_text, parseColor("#000000"))
//        val appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(this)
//        appWidgetManager.updateAppWidget(appWidgetId, views)

        //Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT).show()

    }

}