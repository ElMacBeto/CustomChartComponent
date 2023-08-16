package com.example.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat

class CustomChartComponent : ConstraintLayout {

    private lateinit var chartBar: CustomProgressBar
    private lateinit var chartBackground: View

    //attributes
    private var percentageValue = 0
    private var chartColor = 0

    constructor(context: Context) : super(
        ContextThemeWrapper(
            context,
            null
        )
    ) {
        setWillNotDraw(false)
        setParameters(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        ContextThemeWrapper(
            context,
            null
        ), attrs
    ) {
        setWillNotDraw(false)
        setParameters(attrs)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        drawChartContainer()
        drawBar()
    }

    @SuppressLint("CustomViewStyleable")
    private fun setParameters(attrs: AttributeSet? = null) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomChartComponent)
        percentageValue = a.getInt(R.styleable.CustomChartComponent_percentage_value, 0)
        chartColor = a.getInt(R.styleable.CustomChartComponent_chart_color, 0)
        a.recycle()
    }

    private fun drawChartContainer() {
        chartBackground = View(context)
        chartBackground.id = View.generateViewId()
        chartBackground.setBackgroundResource(R.drawable.chart_container)

        val layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )

        chartBackground.layoutParams = layoutParams

        ViewCompat.setElevation(chartBackground, 10F)

        this.addView(chartBackground)

        val constraintSet = ConstraintSet()
        constraintSet.clone(this)

        constraintSet.connect(
            chartBackground.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        constraintSet.connect(
            chartBackground.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP
        )
        constraintSet.connect(
            chartBackground.id,
            ConstraintSet.END,
            ConstraintSet.PARENT_ID,
            ConstraintSet.END
        )
        constraintSet.connect(
            chartBackground.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM
        )

        constraintSet.applyTo(this)
    }

    private fun drawBar() {
        chartBar = CustomProgressBar(context)
        chartBar.id = View.generateViewId()
        chartBar.setColor(chartColor)
        chartBar.rotation = 180f
        ViewCompat.setElevation(chartBar, 1F)
        this.addView(chartBar)

        val constraintSet = ConstraintSet()
        constraintSet.clone(this)

        constraintSet.connect(
            chartBar.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        constraintSet.connect(
            chartBar.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP
        )
        constraintSet.connect(
            chartBar.id,
            ConstraintSet.END,
            ConstraintSet.PARENT_ID,
            ConstraintSet.END
        )
        constraintSet.connect(
            chartBar.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM
        )

        constraintSet.applyTo(this)
    }

    fun setBarValue(value:Int) {
        if(value <= 100)
            chartBar.setProgress(value)
    }

    fun setColor(newColor:Int){
        chartBar.setColor(newColor)
    }
}