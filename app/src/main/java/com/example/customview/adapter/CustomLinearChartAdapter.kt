package com.example.customview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customview.R
import com.example.customview.model.ChartModel

class CustomLinearChartAdapter(private val dataSet: ArrayList<ChartModel>) :
    RecyclerView.Adapter<CustomLinearChartViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CustomLinearChartViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_lienar_chart, viewGroup, false)

        return CustomLinearChartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomLinearChartViewHolder, position: Int) {
        val chartData = dataSet[position]
        holder.bindData(chartData)
    }

    override fun getItemCount() = dataSet.size

}