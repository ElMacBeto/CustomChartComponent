package com.example.customview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    private var value1 = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val includeChart1 = findViewById<ConstraintLayout>(R.id.chart1)
        val chart1 = includeChart1.findViewById<CustomChartComponent>(R.id.chart)

        val includeChart2 = findViewById<ConstraintLayout>(R.id.chart2)
        val chart2 = includeChart2.findViewById<CustomChartComponent>(R.id.chart)

        val btn = findViewById<Button>(R.id.btn_toggle)


        btn.setOnClickListener{
            value1 += 10
            if (value1 <= 100) {
                chart1.setBarValue(value1)
                chart2.setBarValue(value1/2)
            }else{
                value1=0
                chart1.setBarValue(value1)
                chart2.setBarValue(value1/2)
            }
        }

    }
}