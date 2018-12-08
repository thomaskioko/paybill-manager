package com.thomaskioko.paybillmanager.mobile.ui.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecoration(color: Int, private val dividerHeight: Int) : RecyclerView.ItemDecoration() {
    private val paint: Paint = Paint()

    init {
        paint.color = color
        paint.strokeWidth = dividerHeight.toFloat()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.set(4, 0, 4, dividerHeight)

    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        val left = 40
        val right = parent.width - 20

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child
                    .layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            canvas.drawLine(left.toFloat(), top.toFloat(), right.toFloat(), top.toFloat(), paint)
        }
    }
}
