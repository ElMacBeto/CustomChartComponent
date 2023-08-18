package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils

class CustomRadarChart(context: Context?, attrs: AttributeSet?) :
    RadarChart(context, attrs) {

    private var markColors: Array<Int> = arrayOf(
        R.color.white,
        R.color.white,
        R.color.white,
        R.color.white,
        R.color.white
    )

    private val mFontMetricsBuffer = Paint.FontMetrics()
    private val mDrawTextRectBuffer = Rect()
    private val mAxisLabelPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    fun setMarkColors(colors: Array<Int>) {
        markColors = colors
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        renderXLabelsWithCircles(canvas)
        drawLines()
    }

    private fun drawLines() {
        val drawLabelAnchor = MPPointF.getInstance(0f, 0f)


    }

    private fun renderXLabelsWithCircles(c: Canvas?) {
        if (!mXAxis.isEnabled || !mXAxis.isDrawLabelsEnabled) return

        val drawLabelAnchor = MPPointF.getInstance(0.5f, 0.5f)
        val sliceangle: Float = this.sliceAngle
        val labelRotationAngleDegrees = mXAxis.labelRotationAngle

        val factor = (this.factor) * 0.95f
        val center: MPPointF = this.centerOffsets
        val pOut = MPPointF.getInstance(0f, 0f)

        for (i in 0 until this.data.maxEntryCountSet.entryCount) {
            val angle: Float = (sliceangle * i + this.rotationAngle) % 360f
            val labelColor = markColors[i]
            val label = mXAxis.valueFormatter.getAxisLabel(i.toFloat(), mXAxis)
            Utils.getPosition(
                center, this.yRange * factor
                        + mXAxis.mLabelRotatedWidth / 2f, angle, pOut
            )
            drawCircle(
                c, labelColor, pOut.x, pOut.y,
                drawLabelAnchor
            )
            mAxisLabelPaint.textSize = 24f
            if (i==0){
                Utils.drawXAxisValue(c, label, pOut.x, pOut.y - 50, mAxisLabelPaint, drawLabelAnchor, labelRotationAngleDegrees)
            }else
                Utils.drawXAxisValue(c, label, pOut.x, pOut.y + 50, mAxisLabelPaint, drawLabelAnchor, labelRotationAngleDegrees)
        }
        MPPointF.recycleInstance(center)
        MPPointF.recycleInstance(pOut)
        MPPointF.recycleInstance(drawLabelAnchor)
    }

    private fun drawCircle(
        c: Canvas?,
        color: Int,

        x: Float,
        y: Float,
        anchor: MPPointF?,
    ) {
        var drawOffsetX = 0f
        var drawOffsetY = 0f
        val circlePaint = Paint()
        val lineHeight = circlePaint.getFontMetrics(mFontMetricsBuffer)

        drawOffsetX -= mDrawTextRectBuffer.left.toFloat()
        drawOffsetY += -mFontMetricsBuffer.ascent

        if (anchor!!.x != 0f || anchor.y != 0f) {
            drawOffsetX -= mDrawTextRectBuffer.width() * anchor.x
            drawOffsetY -= lineHeight * anchor.y
        }
        drawOffsetX += x
        drawOffsetY += y
        circlePaint.color = context.getColor(color)
        c!!.drawCircle(drawOffsetX, drawOffsetY, 18f, circlePaint)
    }

}