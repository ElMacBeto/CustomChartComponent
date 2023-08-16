package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

const val VERTICAL_ORIENTATION = 0
const val HORIZONTAL_ORIENTATION = 1

class CustomProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.progressBarStyleHorizontal
) : View(context, attrs, defStyleAttr) {

    private val progressPaint = Paint()
    private var progress:Int = 0
    private var orientation:Int = HORIZONTAL_ORIENTATION


    fun setProgress(newProgress: Int) {
        progress = newProgress
        invalidate()
    }

    fun setColor(color:Int){
        progressPaint.color = if (color != 0) color else Color.BLUE
    }

    fun setOrientation(newOrientation:Int){
        orientation = newOrientation
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()

        if (orientation == HORIZONTAL_ORIENTATION){
            val progressHeight = height * (progress.toFloat() / 100)
            canvas.drawRect(0f, 0f, width, progressHeight, progressPaint)
        }else{
            val progressWidth = width * (progress.toFloat() / 100)
            canvas.drawRect(0f, 0f, progressWidth, height, progressPaint)

        }
    }
}