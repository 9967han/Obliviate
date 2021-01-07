package com.example.obliviate

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.obliviate.databinding.CustomDialogBinding

class MyCustomDialog(context: Context, myCustomDialogInterface: MyCustomDialogInterface, binding: CustomDialogBinding): Dialog(context), View.OnClickListener {

    private var myCustomDialogInterface: MyCustomDialogInterface? = null
    private var binding: CustomDialogBinding? = null

    init {
        this.myCustomDialogInterface = myCustomDialogInterface
        this.binding = binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog)

        //배경 투명
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding?.widgetRegister?.setOnClickListener(this)
        binding?.widgetSize?.setOnClickListener(this)
    }

    override fun onClick(view : View?) {
        Log.d("로그", "2")
        when(view) {
            binding?.widgetRegister -> {
                Log.d("로그", "2")
                this.myCustomDialogInterface?.onRegisterBtnClicked()
            }
        }
    }
}