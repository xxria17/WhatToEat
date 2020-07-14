package com.example.whattoeat

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.util.*

class RouletteActivity : AppCompatActivity() {

    private lateinit var pieDataSet: PieDataSet
    private lateinit var pieData: PieData
    private lateinit var pieChart: PieChart

    private lateinit var rotateButton: Button
    private lateinit var nextButton: Button
    private lateinit var againButton: Button

    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roulette)

        init()
        setPieChart()

        rotateButton.setOnClickListener {
            rotationRoulette()
            settingButton()
        }
        againButton.setOnClickListener {
            rotationRoulette()
        }
        nextButton.setOnClickListener {
            val intent: Intent = Intent(this, RandomActivity::class.java)
//            intent.putExtra()
            startActivity(intent)
        }
        backButton.setOnClickListener{
            finish()
        }
    }

    private fun init() {
        rotateButton = findViewById(R.id.rotate_button)
        againButton = findViewById(R.id.again_button)
        nextButton = findViewById(R.id.next_button)

        backButton = findViewById(R.id.back_button)

        againButton.visibility = View.GONE
        nextButton.visibility = View.GONE
    }

    private fun setPieChart() {
        val entry = mutableListOf<PieEntry>()

        entry.add(PieEntry(2f, "한식"))
        entry.add(PieEntry(2f, "양식"))
        entry.add(PieEntry(2f, "중식"))
        entry.add(PieEntry(2f, "일식"))
        entry.add(PieEntry(2f, "분식"))
        entry.add(PieEntry(2f, "패스트푸드"))
        entry.add(PieEntry(2f, "야식"))
        entry.add(PieEntry(2f, "기타"))

        pieDataSet = PieDataSet(entry, "")
        val baseColor: BaseColor = BaseColor()
        val chartColor = baseColor.CHART_COLOR.toMutableList()

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
            setEntryLabelColor(Color.DKGRAY)
            setEntryLabelTextSize(16f)
        }
        pieChart.legend.isEnabled = false
        pieDataSet.setDrawValues(false)
        pieChart.invalidate()
    }

    private fun rotationRoulette() {
        var random: Random = Random()
        val randomNum = random.nextInt(360) + 1440
        Log.d("!!!", randomNum.toString())
        pieChart.spin(1000, -19f, -randomNum.toFloat(), Easing.EaseInOutQuad)
//        pieChart.spin(500, -19f, -19f, Easing.EaseInOutQuad)
    }

    private fun settingButton() {
        againButton.visibility = View.VISIBLE
        nextButton.visibility = View.VISIBLE
        rotateButton.visibility = View.GONE
    }


}
