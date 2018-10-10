package com.thomaskioko.paybillmanager.mobile.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.thomaskioko.paybillmanager.mobile.R

class CustomKeyboardView : LinearLayout, CustomKeyboard.KeyboardListener {

    private var keyboard: CustomKeyboard? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.view_custom_keyborad, this)
        keyboard = findViewById(R.id.keyboard)
        keyboard!!.setKeyboardListener(this)

    }

    /**
     * only register TextView/EditText
     *
     * @param view
     */
    private fun registerInputView(view: TextView) {
        if (keyboard != null) {
            keyboard!!.registerTextView(view)
        }
    }


    /**
     * show keyboard and register TextView/EditText
     *
     * @param view
     */
    fun showKeyboard(view: TextView) {
        registerInputView(view)
    }


    override fun onOKResult(amount: String) {
        //TODO:: Show BottomSheet

    }

}