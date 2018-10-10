package com.thomaskioko.paybillmanager.mobile.ui.view

import android.content.Context
import android.graphics.*
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.util.AttributeSet
import android.widget.TextView
import com.thomaskioko.paybillmanager.mobile.R
import timber.log.Timber
import java.lang.reflect.Field
import java.text.NumberFormat
import java.util.*

class CustomKeyboard(context: Context, attrs: AttributeSet) : KeyboardView(context, attrs) {
    private val contentStr = StringBuilder()
    private var currentTextView: TextView? = null
    private var keyboardListener: KeyboardListener? = null
    private var customKeyboard: Keyboard? = null
    private lateinit var mContext: Context

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
        mContext = context
        val mKeyboard = Keyboard(context, R.xml.keyboard)
        onKeyboardActionListener = mOnKeyboardActionListener
        keyboard = mKeyboard
        isPreviewEnabled = false
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        customKeyboard = this.keyboard
        customKeyboard = this.keyboard
        var keys: List<Keyboard.Key>? = null
        if (customKeyboard != null) {
            keys = customKeyboard!!.keys
        }

        if (keys != null) {
            for (key in keys) {
                if (key.codes[0] == -4) {
                    drawKeyBackground(R.drawable.key_bg, canvas, key)
                    drawText(canvas, key)
                }
            }
        }
    }

    interface KeyboardListener {
        fun onOKResult(amount: String)
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

    fun setKeyboardListener(keyboardListener: KeyboardListener) {
        this.keyboardListener = keyboardListener
    }

    fun deleteCharacter() {
        if (contentStr.isNotEmpty()) {
            contentStr.delete(contentStr.length - 1, contentStr.length)
            currentTextView!!.text = contentStr.toString()
        }
    }

    private fun drawKeyBackground(drawableId: Int, canvas: Canvas, key: Keyboard.Key) {
        val npd = mContext.resources.getDrawable(
                drawableId)
        val drawableState = key.currentDrawableState
        if (key.codes[0] != 0) {
            npd.state = drawableState
        }
        npd.setBounds(key.x, key.y, key.x + key.width, key.y + key.height)
        npd.draw(canvas)
    }

    private fun drawText(canvas: Canvas, key: Keyboard.Key) {
        val bounds = Rect()
        val paint = Paint()
        paint.textAlign = Paint.Align.CENTER
        paint.isAntiAlias = true
        paint.color = Color.WHITE
        paint.textSize = 28.toFloat()


        if (key.label != null) {
            val label = key.label.toString()

            val field: Field

            if (label.length > 1 && key.codes.size < 2) {
                var labelTextSize = 0
                try {
                    field = KeyboardView::class.java.getDeclaredField("mLabelTextSize")
                    field.isAccessible = true
                    labelTextSize = field.get(this) as Int
                } catch (e: NoSuchFieldException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

                paint.textSize = (labelTextSize + 20).toFloat()
                paint.typeface = Typeface.DEFAULT_BOLD
            } else {
                var keyTextSize = 0
                try {
                    field = KeyboardView::class.java.getDeclaredField("mLabelTextSize")
                    field.isAccessible = true
                    keyTextSize = field.get(this) as Int
                } catch (e: NoSuchFieldException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

                paint.textSize = keyTextSize.toFloat()
                paint.typeface = Typeface.DEFAULT
            }

            paint.getTextBounds(key.label.toString(), 0, key.label.toString()
                    .length, bounds)
            canvas.drawText(key.label.toString(), (key.x + key.width / 2).toFloat(),
                    (key.y + key.height / 2 + bounds.height() / 2).toFloat(), paint)
        } else if (key.icon != null) {
            key.icon.setBounds(key.x + (key.width - key.icon.intrinsicWidth) / 2, key.y + (key.height - key.icon.intrinsicHeight) / 2,
                    key.x + (key.width - key.icon.intrinsicWidth) / 2 + key.icon.intrinsicWidth, key.y + (key.height - key.icon.intrinsicHeight) / 2 + key.icon.intrinsicHeight)
            key.icon.draw(canvas)
        }

    }

}