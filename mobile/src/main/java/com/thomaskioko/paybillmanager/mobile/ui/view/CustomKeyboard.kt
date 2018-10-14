package com.thomaskioko.paybillmanager.mobile.ui.view

import android.content.Context
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.util.AttributeSet
import android.widget.TextView
import com.thomaskioko.paybillmanager.mobile.R
import timber.log.Timber
import java.text.NumberFormat
import java.util.*

class CustomKeyboard(context: Context, attrs: AttributeSet) : KeyboardView(context, attrs) {
    private val contentStr = StringBuilder()
    private var currentTextView: TextView? = null
    private var keyboardListener: CustomKeyboardView.KeyboardListener? = null

    private val mOnKeyboardActionListener = object : KeyboardView.OnKeyboardActionListener {

        override fun onPress(primaryCode: Int) {
            if (currentTextView != null) {
                when (primaryCode) {
                    0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> {
                        contentStr.append(primaryCode.toString() + "")
                    }
                    10 -> {
                        deleteCharacter()
                    }
                    11 -> {
                    }
                    12 -> {
                        keyboardListener!!.onOKResult(contentStr.toString())
                    }
                    else -> {
                        contentStr.append("")
                    }
                }

                val numberFormat = NumberFormat.getNumberInstance(Locale.US)
                if (!contentStr.toString().isEmpty())
                    currentTextView!!.text = numberFormat.format(contentStr.toString().toInt())
            } else {
                Timber.e("You didn't register textView!")
            }
        }


        override fun onRelease(primaryCode: Int) {

        }

        override fun onKey(primaryCode: Int, keyCodes: IntArray) {

        }

        override fun onText(text: CharSequence) {

        }

        override fun swipeLeft() {

        }

        override fun swipeRight() {

        }

        override fun swipeDown() {

        }

        override fun swipeUp() {

        }
    }

    init {
        init(context)
    }

    private fun init(context: Context) {
        val mKeyboard = Keyboard(context, R.xml.keyboard)
        onKeyboardActionListener = mOnKeyboardActionListener
        keyboard = mKeyboard
        isPreviewEnabled = false
    }

    /**
     * register textview
     *
     * @param textView
     */
    fun registerTextView(textView: TextView) {
        currentTextView = textView
        deleteCharacter()
    }

    fun setKeyboardListener(keyboardListener: CustomKeyboardView.KeyboardListener) {
        this.keyboardListener = keyboardListener
    }

    fun deleteCharacter() {
        if (contentStr.isNotEmpty()) {
            contentStr.delete(contentStr.length - 1, contentStr.length)
            currentTextView!!.text = contentStr.toString()
        }
    }

}