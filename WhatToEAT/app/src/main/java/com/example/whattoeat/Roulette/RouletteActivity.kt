package com.example.whattoeat.Roulette

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.whattoeat.R
import com.github.mikephil.charting.charts.PieChart

class RouletteActivity : AppCompatActivity() {

    private lateinit var rotateButton: Button
    private lateinit var nextButton: Button
    private lateinit var againButton: Button

    private lateinit var resultTextView: TextView
    private lateinit var pieChart: PieChart

    private val rouletteViewModel = RouletteViewModel()
    private var currentCase = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roulette)

        init()
        currentCase = "Classification"
        rouletteViewModel.setChartUI(currentCase, this@RouletteActivity, pieChart, "")

        rotateButton.setOnClickListener {
            rotate()
            settingButton(0)
        }

        nextButton.setOnClickListener {
            if (nextButton.text.equals(resources.getString(R.string.go_home))) {
                finish()
            } else {
                nextButton.text = resources.getString(R.string.go_home)
                currentCase = "Detail"
                rouletteViewModel.setChartUI(currentCase, this@RouletteActivity, pieChart,
                    resultTextView.text as String
                )
                settingButton(1)
            }
        }

        againButton.setOnClickListener {
            rotate()
        }
    }

    private fun rotate(){
        val result: String = rouletteViewModel.rotateRoulette(currentCase, pieChart)
        resultTextView.postDelayed(Runnable {
            run() {
                resultTextView.text = result
                resultTextView.visibility = View.VISIBLE
            }
        }, 850)
    }

    private fun init() {
        rotateButton = findViewById(R.id.rotate_button)
        nextButton = findViewById(R.id.next_button)
        againButton = findViewById(R.id.again_button)
        resultTextView = findViewById(R.id.result_textview)

        pieChart = findViewById(R.id.pieChart)

        againButton.visibility = View.GONE
        nextButton.visibility = View.GONE
        resultTextView.visibility = View.GONE
    }

    private fun settingButton(case: Int) {
        if (case == 0) {
            againButton.visibility = View.VISIBLE
            nextButton.visibility = View.VISIBLE
            rotateButton.visibility = View.GONE
        } else {
            againButton.visibility = View.GONE
            nextButton.visibility = View.GONE
            resultTextView.visibility = View.GONE
            rotateButton.visibility = View.VISIBLE
        }

    }
}
