package com.example.obliviate

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.obliviate.databinding.ActivityMainBinding
import com.example.obliviate.databinding.CustomDialogBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class MainActivity : AppCompatActivity(), MyCustomDialogInterface {
    var db = FirebaseFirestore.getInstance()
    private lateinit var binding: ActivityMainBinding
    private lateinit var customDialogBinding: CustomDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        customDialogBinding = CustomDialogBinding.inflate(layoutInflater)
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
        val myCustomDialog = MyCustomDialog(this, this, customDialogBinding)
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

    override fun onRegisterBtnClicked() {
        Log.d("로그", "1")
        Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT).show()
    }
}