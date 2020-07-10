package com.example.whattoeat

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class RouletteActivity : AppCompatActivity() {

//    private lateinit var restList: List<String>
    private lateinit var pieDataSet: PieDataSet
    private lateinit var pieData: PieData
    private lateinit var pieChart: PieChart

    private lateinit var rotateButton: Button
//    private lateinit var intentList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roulette)

        rotateButton = findViewById(R.id.rotate_button)
//        intentList = intent.getStringArrayListExtra("restaurant")
        setPieChart()

        rotateButton.setOnClickListener {
            rotationRoulette()
        }
    }

    private fun setPieChart(){
        val entry = mutableListOf<PieEntry>()
//        val cnt: Float = (100 / intentList.size).toFloat()

//        for (item in intentList) {
//            entry.add(PieEntry(cnt, item))
//        }

        entry.add(PieEntry(2f, "한식"))
        entry.add(PieEntry(2f, "양식"))
        entry.add(PieEntry(2f, "중식"))
        entry.add(PieEntry(2f, "일식"))
        entry.add(PieEntry(2f, "분식"))

        pieDataSet = PieDataSet(entry, "")
        val chartColor = ColorTemplate.PASTEL_COLORS.toMutableList()
//        chartColor.add(ContextCompat.getColor(requireContext(), R.color.colorPrimary))

        pieDataSet.apply {
            colors = chartColor
        }

        pieData = PieData(pieDataSet)
        pieChart = findViewById(R.id.pieChart)
        pieChart.data = pieData

        pieChart.apply {
            description = null
            holeRadius = 0f
            centerText = ""
            isDrawHoleEnabled = false
            setEntryLabelColor(Color.WHITE)
            setEntryLabelTextSize(16f)
        }
        pieChart.invalidate()
    }

    private fun rotationRoulette() {
        val animation: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate)
        var randomNum : Float = Math.random().toFloat() + 1000
//        animation.repeatCount = randomNum

//        pieChart.spin(1000, 0f, randomNum, Easing.EaseInOutQuad)
        pieChart.spin(1000, 0f, 1000f, Easing.EasingFunction {
            Easing.EaseInQuad
            randomNum })
    }

}
