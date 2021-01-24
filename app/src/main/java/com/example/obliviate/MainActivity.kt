package com.example.obliviate

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.obliviate.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
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

    override fun onRegisterBtnClicked() {
        Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT).show()
    }
}