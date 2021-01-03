package com.example.obliviate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.obliviate.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
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
                    for (document in task.result!!) {
                        binding.mainTextview.text = unescape(document.data["string"].toString())
                    }
                } else {
                    Log.d("aerror", "Error getting documents.", task.exception)
                }
            }
    }

    private fun unescape(description: String): String? {
        return description.replace("\\\\n".toRegex(), "\\\n")
    }
}