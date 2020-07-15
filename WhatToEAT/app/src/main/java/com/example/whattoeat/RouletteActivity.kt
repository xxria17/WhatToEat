package com.example.whattoeat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class RouletteActivity : AppCompatActivity() {

    private lateinit var pieDataSet: PieDataSet
    private lateinit var pieData: PieData
    private lateinit var pieChart: PieChart

    private lateinit var rotateButton: Button
    private lateinit var nextButton: Button
    private lateinit var againButton: Button
    private lateinit var resultTextView: TextView

    private val baseRoulette = BaseRoulette()
    private val foodList = listOf("한식", "양식", "중식", "일식", "분식", "패스트푸드", "야식", "기타")
    private var resultString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roulette)

        init()
        setRouletteData()

        rotateButton.setOnClickListener {
            rotationRoulette()
            settingButton()
        }
        againButton.setOnClickListener {
            rotationRoulette()
        }
        nextButton.setOnClickListener {
            val intent = Intent(this, RandomActivity::class.java)
            intent.putExtra("result", resultString)
            startActivity(intent)
        }
    }

    private fun init() {
        rotateButton = findViewById(R.id.rotate_button)
        againButton = findViewById(R.id.again_button)
        nextButton = findViewById(R.id.next_button)

        resultTextView = findViewById(R.id.result_textview)
        pieChart = findViewById(R.id.pieChart)

        againButton.visibility = View.GONE
        nextButton.visibility = View.GONE
        resultTextView.visibility = View.GONE
    }

    private fun setRouletteData() {
        val entry = mutableListOf<PieEntry>()

        for (food in foodList) {
            entry.add(PieEntry(2f, food))
        }

        pieDataSet = PieDataSet(entry, "")
        pieData = PieData(pieDataSet)

        baseRoulette.setPieChart(pieData, pieDataSet, pieChart)
    }

    private fun rotationRoulette() {
        val randomNum = baseRoulette.rotateRoulette(pieChart)
//        pieChart.spin(500, 0f, 0f, Easing.EaseInOutQuad)
        resultString = getResult(randomNum)
        resultTextView.postDelayed(Runnable {
            run() {
                resultTextView.text = resultString
                resultTextView.visibility = View.VISIBLE
            }
        }, 850)
    }

    private fun settingButton() {
        againButton.visibility = View.VISIBLE
        nextButton.visibility = View.VISIBLE
        rotateButton.visibility = View.GONE
    }

    private fun getResult(randomNum: Int): String {
        val resultNum = randomNum - 1440
        var result = ""

        /*
        0 ~ 45 : 야식
        46 ~ 90 : 기타
        91 ~ 135 : 한식
        136 ~ 180 : 양식
        181 ~ 225 : 중식
        226 ~ 270 : 일식
        271 ~ 315 : 분식
        316 ~ 360 : 패스트푸드
        */

        if (resultNum <= 360) {
            when (resultNum) {
                in 0..45 -> result = "야식"
                in 46..90 -> result = "기타"
                in 91..135 -> result = "한식"
                in 136..180 -> result = "양식"
                in 181..225 -> result = "중식"
                in 226..270 -> result = "일식"
                in 271..315 -> result = "분식"
                in 316..360 -> result = "패스트푸드"
            }
        }

        return result
    }

}
