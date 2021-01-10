package com.example.obliviate

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.obliviate.databinding.CustomDialogBinding

class MyCustomDialog(context: Context, myCustomDialogInterface: MyCustomDialogInterface): Dialog(context), View.OnClickListener {

    private var myCustomDialogInterface: MyCustomDialogInterface? = null
    private lateinit var binding: CustomDialogBinding

    init {
        this.myCustomDialogInterface = myCustomDialogInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //배경 투명
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding?.widgetRegister?.setOnClickListener(this)
        binding?.widgetSize?.setOnClickListener(this)
    }

    override fun onClick(view : View?) {
        when(view) {
            binding?.widgetRegister -> {
                this.myCustomDialogInterface?.onRegisterBtnClicked()
            }
        }
    }
}