package com.example.obliviate

//import com.skydoves.colorpickerview.ColorPickerDialog
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.obliviate.databinding.CustomDialogBinding
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.util.ColorUtil.parseColor


class MyCustomDialog(context: Context, myCustomDialogInterface: MyCustomDialogInterface, mainText: String): Dialog(
        context
), View.OnClickListener {

    private var myCustomDialogInterface: MyCustomDialogInterface? = null
    private var mainText : String
    private lateinit var binding: CustomDialogBinding

    init {
        this.myCustomDialogInterface = myCustomDialogInterface
        this.mainText = mainText
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var customDialogBackgrondColor = PreferenceManager.getString(context, "customDialogBackgrondColor")
        var customDialogTextColor = PreferenceManager.getString(context, "customDialogTextColor")
        if (customDialogBackgrondColor == "") {
            customDialogBackgrondColor = context.getResources().getString(R.color.ivory);
        }
        if (customDialogTextColor == "") {
            customDialogTextColor = context.getResources().getString(R.color.textColor);
        }
        binding = CustomDialogBinding.inflate(layoutInflater)
        binding?.dialogText.setBackgroundColor(parseColor(customDialogBackgrondColor))
        binding?.dialogText.setTextColor(parseColor(customDialogTextColor))
        val view = binding.root
        setContentView(view)

        //배경 투명
        binding?.dialogText.setText(this.mainText)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding?.dialogWidgetRegisterButton?.setOnClickListener(this)

        binding?.dialogBackgroundColorBtn1?.setOnClickListener(this)
        binding?.dialogBackgroundColorBtn2?.setOnClickListener(this)
        binding?.dialogBackgroundColorBtn3?.setOnClickListener(this)
        binding?.dialogBackgroundColorBtn4?.setOnClickListener(this)
        binding?.dialogBackgroundColorBtn5?.setOnClickListener(this)
        binding?.dialogBackgroundColorBtn6?.setOnClickListener(this)
        binding?.dialogBackgroundColorBtn7?.setOnClickListener(this)
        binding?.dialogBackgroundColorBtn8?.setOnClickListener(this)
        binding?.dialogBackgroundColorBtn9?.setOnClickListener(this)
        binding?.dialogBackgroundColorpickerBtn?.setOnClickListener(this)


        binding?.dialogTextColorBtn1?.setOnClickListener(this)
        binding?.dialogTextColorBtn2?.setOnClickListener(this)
        binding?.dialogTextColorBtn3?.setOnClickListener(this)
        binding?.dialogTextColorBtn4?.setOnClickListener(this)
        binding?.dialogTextColorBtn5?.setOnClickListener(this)
        binding?.dialogTextColorBtn6?.setOnClickListener(this)
        binding?.dialogTextColorBtn7?.setOnClickListener(this)
        binding?.dialogTextColorBtn8?.setOnClickListener(this)
        binding?.dialogTextColorBtn9?.setOnClickListener(this)
        binding?.dialogTextColorpickerBtn?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view) {
            binding?.dialogWidgetRegisterButton -> {
                val backgroundColor = binding?.dialogText.background as ColorDrawable
                val textColor = binding?.dialogText.currentTextColor
                val hexBackgroundColor = String.format("#%06X", 0xFFFFFF and backgroundColor.color)
                val hexTextColor = String.format("#%06X", 0xFFFFFF and textColor)
                this.myCustomDialogInterface?.onRegisterBtnClicked(hexBackgroundColor, hexTextColor)
                PreferenceManager.setString(context, "customDialogBackgrondColor", hexBackgroundColor)
                PreferenceManager.setString(context, "customDialogTextColor", hexTextColor)
            }

            binding?.dialogBackgroundColorBtn1 -> {
                binding?.dialogText?.setBackgroundColor(context.resources.getColor(R.color.dialogColor1))
            }

            binding?.dialogBackgroundColorBtn2 -> {
                binding?.dialogText?.setBackgroundColor(context.resources.getColor(R.color.dialogColor2))
            }

            binding?.dialogBackgroundColorBtn3 -> {
                binding?.dialogText?.setBackgroundColor(context.resources.getColor(R.color.dialogColor3))
            }

            binding?.dialogBackgroundColorBtn4 -> {
                binding?.dialogText?.setBackgroundColor(context.resources.getColor(R.color.dialogColor4))
            }

            binding?.dialogBackgroundColorBtn5 -> {
                binding?.dialogText?.setBackgroundColor(context.resources.getColor(R.color.dialogColor5))
            }

            binding?.dialogBackgroundColorBtn6 -> {
                binding?.dialogText?.setBackgroundColor(context.resources.getColor(R.color.dialogColor6))
            }

            binding?.dialogBackgroundColorBtn7 -> {
                binding?.dialogText?.setBackgroundColor(context.resources.getColor(R.color.dialogColor7))
            }

            binding?.dialogBackgroundColorBtn8 -> {
                binding?.dialogText?.setBackgroundColor(context.resources.getColor(R.color.dialogColor8))
            }

            binding?.dialogBackgroundColorBtn9 -> {
                binding?.dialogText?.setBackgroundColor(context.resources.getColor(R.color.dialogColor9))
            }

            binding?.dialogBackgroundColorpickerBtn -> {

                ColorPickerDialog
                        .Builder(context)                        // Pass Activity Instance
                        .setTitle("배경 색 설정")  // Default ColorShape.CIRCLE
                        .setDefaultColor(parseColor("#ffffff"))                // Default "Choose Color"
                        .setColorShape(ColorShape.CIRCLE)   // Default ColorShape.CIRCLE
                        .setColorListener { color, colorHex ->
                            binding?.dialogText?.setBackgroundColor(color)
                        }
                        .show()
            }


            binding?.dialogTextColorBtn1 -> {
                binding?.dialogText?.setTextColor(context.resources.getColor(R.color.dialogColor1))
            }

            binding?.dialogTextColorBtn2 -> {
                binding?.dialogText?.setTextColor(context.resources.getColor(R.color.dialogColor2))
            }

            binding?.dialogTextColorBtn3 -> {
                binding?.dialogText?.setTextColor(context.resources.getColor(R.color.dialogColor3))
            }

            binding?.dialogTextColorBtn4 -> {
                binding?.dialogText?.setTextColor(context.resources.getColor(R.color.dialogColor4))
            }

            binding?.dialogTextColorBtn5 -> {
                binding?.dialogText?.setTextColor(context.resources.getColor(R.color.dialogColor5))
            }

            binding?.dialogTextColorBtn6 -> {
                binding?.dialogText?.setTextColor(context.resources.getColor(R.color.dialogColor6))
            }

            binding?.dialogTextColorBtn7 -> {
                binding?.dialogText?.setTextColor(context.resources.getColor(R.color.dialogColor7))
            }

            binding?.dialogTextColorBtn8 -> {
                binding?.dialogText?.setTextColor(context.resources.getColor(R.color.dialogColor8))
            }

            binding?.dialogTextColorBtn9 -> {
                binding?.dialogText?.setTextColor(context.resources.getColor(R.color.dialogColor9))
            }


            binding?.dialogTextColorpickerBtn -> {
                ColorPickerDialog
                        .Builder(context)                        // Pass Activity Instance
                        .setTitle("글자 색 설정")  // Default ColorShape.CIRCLE
                        .setDefaultColor(parseColor("#ffffff"))                // Default "Choose Color"
                        .setColorShape(ColorShape.CIRCLE)   // Default ColorShape.CIRCLE
                        .setColorListener { color, colorHex ->
                            binding?.dialogText?.setTextColor(color)
                        }
                        .show()
            }

        }
    }
}


