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

class RandomActivity : AppCompatActivity() {

    private lateinit var pieDataSet: PieDataSet
    private lateinit var pieData: PieData
    private lateinit var pieChart: PieChart

    private lateinit var rotateButton: Button
    private lateinit var homeButton: Button
    private lateinit var againButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var backButton: ImageView

    private var resultString: String = ""
    private var foodList = listOf("한식", "양식", "중식", "일식", "분식", "패스트푸드", "야식", "기타")
    private val baseRoulette = BaseRoulette()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        resultString = intent.getStringExtra("result")
        init()
        setUI(resultString)


        backButton.setOnClickListener {
            finish()
        }
        homeButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        rotateButton.setOnClickListener {
            rotationRoulette()
            settingButton()
        }
        againButton.setOnClickListener {
            rotationRoulette()
        }


    }

    private fun setUI(result: String) {
        val entry = mutableListOf<PieEntry>()

        when (result) {
            "한식" -> foodList = listOf("알탕", "부대찌개", "김치찌개", "두루치기", "해장국", "감자탕", "냉면", "갈비찜", "순대국밥", "닭갈비", "고기", "찜닭", "닭볶음탕")
            "양식" -> foodList = listOf("토마토 스파게티", "까르보나라", "샐러드", "로제 파스타", "스테이크")
            "중식" -> foodList = listOf("짜장면", "짬뽕", "탕수육", "새우볶음밥", "짬뽕밥", "마파두부", "양장피", "깐풍기", "팔보채", "유산슬", "라조기", "울면")
            "일식" -> foodList = listOf("우동", "초밥", "히레까스", "냉모밀", "가츠동", "생선까스", "로스까스", "김치나베우동")
            "분식" -> foodList = listOf("김밥", "라면", "떡볶이", "오므라이스", "덮밥", "만두", "핫도그", "오뎅")
            "패스트푸드" -> foodList = listOf("피자", "치킨", "서브웨이", "에그드랍", "햄버거")
            "야식" -> foodList = listOf("닭발", "족발", "보쌈", "막창", "빙수")
            "기타" -> foodList = listOf("쌀국수", "마라탕", "월남쌈", "도시락", "카레")
        }

        for (food in foodList) {
            entry.add(PieEntry(2f, food))
        }

        pieDataSet = PieDataSet(entry, "")
        pieData = PieData(pieDataSet)

        baseRoulette.setPieChart(pieData, pieDataSet, pieChart)

    }

    private fun init() {
        rotateButton = findViewById(R.id.rotate_button_random)
        againButton = findViewById(R.id.again_button_random)
        homeButton = findViewById(R.id.next_button_random)
        backButton = findViewById(R.id.back_button_random)

        resultTextView = findViewById(R.id.result_textview_random)
        pieChart = findViewById(R.id.pieChart_random)

        againButton.visibility = View.GONE
        homeButton.visibility = View.GONE
        resultTextView.visibility = View.GONE
    }

    private fun rotationRoulette() {
        val randomNum = baseRoulette.rotateRoulette(pieChart)
        resultString = getResult(randomNum)
        resultTextView.postDelayed(Runnable {
            run() {
                resultTextView.text = resultString
                resultTextView.visibility = View.VISIBLE
            }
        }, 850)
    }

    private fun getResult(randomNum: Int): String {
        val resultNum = randomNum - 1440
        val result = ""


        return result
    }

    private fun settingButton() {
        againButton.visibility = View.VISIBLE
        homeButton.visibility = View.VISIBLE
        rotateButton.visibility = View.GONE
    }
}
