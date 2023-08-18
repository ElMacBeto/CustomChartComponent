package com.example.customview

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customview.adapter.CustomLinearChartAdapter
import com.example.customview.model.ChartModel
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.card.MaterialCardView


class MainActivity : AppCompatActivity() {


    private lateinit var chart1:CustomChartComponent
    private lateinit var chart2:CustomChartComponent
    private lateinit var radarChart: CustomRadarChart
    private lateinit var rvCharts:RecyclerView
    private lateinit var adapter:CustomLinearChartAdapter
    private lateinit var chart2axie:TextView
    private lateinit var chart2Value:TextView
    private lateinit var chart2Title:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindView()
        setRadarChars()
        setCustomCharts()
        setChartsRV()
    }

    private fun bindView() {
        val includeChart1 = findViewById<MaterialCardView>(R.id.chart1)
        chart1 = includeChart1.findViewById(R.id.chart)
        val includeChart2 = findViewById<MaterialCardView>(R.id.chart2)
        chart2 = includeChart2.findViewById(R.id.chart)
        radarChart = findViewById(R.id.radar_chart)
        rvCharts = findViewById(R.id.rv_linear_charts)
        chart2axie = includeChart2.findViewById(R.id.tv_axis_label)
        chart2Value = includeChart2.findViewById(R.id.tv_value_label)
        chart2Title = includeChart2.findViewById(R.id.tv_chart_title)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setRadarChars(){
        val labels = arrayOf("H2O", "Social", "Media", "Economic", "CO2 EQ")
        val colors = arrayOf(R.color.chart1,R.color.chart2,R.color.chart3,R.color.chart4,R.color.chart5)

        val values = listOf(80f, 40f, 60f, 60f, 90f)
        val entries = mutableListOf<RadarEntry>()

        for (i in values.indices) {
            entries.add(RadarEntry(values[i]))
        }

        val radarDataSet = RadarDataSet(entries, null)
        radarDataSet.color = getColor(android.R.color.transparent)
        radarDataSet.fillAlpha = 160
        radarDataSet.fillDrawable = getDrawable(R.drawable.drawable_gradient_color_chart)
        radarDataSet.setDrawFilled(true)

        radarDataSet.valueTextSize = 10f
        radarDataSet.isDrawHighlightCircleEnabled = true
        radarDataSet.setDrawHighlightIndicators(false)

        val xAxis = radarChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.textColor = getColor(android.R.color.transparent)

        val yAxis = radarChart.yAxis
        yAxis.isEnabled = false
        yAxis.axisMinimum = 0f

        val radarData = RadarData(radarDataSet)
        radarChart.data = radarData
        radarChart.description.isEnabled = false
        radarChart.webLineWidth = 1f
        radarChart.webColor = getColor(R.color.black)
        radarChart.webLineWidthInner = 1f
        radarChart.webColorInner = Color.LTGRAY
        radarChart.isRotationEnabled = false
        radarChart.setMarkColors(colors)
        radarChart.invalidate()
    }

    @SuppressLint("SetTextI18n")
    private fun setCustomCharts(){
        chart1.setBarValue(90)
        chart2.setBarValue(40)
        chart2.setColor(getColor(R.color.chart3))
        chart2axie.text = "Social"
        chart2Value.text = "40%"
        chart2Title.text = "Area de mejora"
    }

    private fun setChartsRV() {
        val  chartsModels = arrayListOf(
            ChartModel(100f,"CO2 EQ",R.color.chart1),
            ChartModel(80f,"H2O",R.color.chart2),
            ChartModel(40f,"SOCIAL",R.color.chart3),
            ChartModel(60f,"MEDIA",R.color.chart4),
            ChartModel(60f,"ECONOMIC",R.color.chart5)
        )
        adapter = CustomLinearChartAdapter(chartsModels)
        rvCharts.layoutManager =  LinearLayoutManager(this)
        rvCharts.adapter = adapter
    }
}