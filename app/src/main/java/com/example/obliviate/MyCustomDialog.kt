package com.example.obliviate

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.example.obliviate.databinding.CustomDialogBinding
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.util.ColorUtil.parseColor

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
        binding?.dialogWidgetRegisterButton?.setOnClickListener(this)
        binding?.dialogColorBtn1?.setOnClickListener(this)
        binding?.dialogColorBtn2?.setOnClickListener(this)
    }

    override fun onClick(view : View?) {
        when(view) {
            binding?.dialogWidgetRegisterButton -> {
                this.myCustomDialogInterface?.onRegisterBtnClicked()
            }

            binding?.dialogColorBtn1 -> {
                binding?.dialogText?.setBackgroundResource(R.drawable.dialog1)
            }

            binding?.dialogColorBtn2 -> {
                ColorPickerDialog
                    .Builder(context)        				// Pass Activity Instance
                    .setTitle("배경 색 설정")  // Default ColorShape.CIRCLE
                    .setDefaultColor(parseColor("#ffffff"))            	// Default "Choose Color"
                    .setColorShape(ColorShape.CIRCLE)   // Default ColorShape.CIRCLE
                    .setColorListener { color, colorHex ->
                        // Handle Color Selection
                    }
                    .show()
            }

        }
    }
}


