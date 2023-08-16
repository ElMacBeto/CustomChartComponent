package com.example.customview.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customview.CustomProgressBar
import com.example.customview.R
import com.example.customview.VERTICAL_ORIENTATION
import com.example.customview.model.ChartModel

class CustomLinearChartViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val chartTitleLabel: TextView
    private val chartValueLabel: TextView
    private val customBar: CustomProgressBar
    private val ctx = view.context

    init {
        chartTitleLabel = view.findViewById(R.id.tv_title)
        chartValueLabel = view.findViewById(R.id.tv_value_label)
        customBar = view.findViewById(R.id.custom_bar)
    }
    fun bindData(chartModel: ChartModel){
        chartTitleLabel.text = chartModel.label
        chartValueLabel.text = chartModel.value.toString()
        customBar.setColor(ctx.getColor(chartModel.color))
        customBar.setOrientation(VERTICAL_ORIENTATION)
        customBar.setProgress(chartModel.value.toInt())
    }
}