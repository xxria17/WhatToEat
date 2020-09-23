package com.example.whattoeat.Roulette

import android.app.Activity
import com.example.whattoeat.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class RouletteViewModel {

    private val baseRoulette = BaseRoulette()
    private lateinit var pieData: PieData
    private lateinit var pieDataSet: PieDataSet

    private lateinit var foodList: List<String>

    private val CLASSFICATION: String = "Classification"
    private val DETAIL : String = "Detail"

    public fun setChartUI(data: String, activity: Activity, pieChart: PieChart, result: String) {
        val entry = mutableListOf<PieEntry>()

        when (data) {
            CLASSFICATION -> foodList = activity.resources.getString(R.string.food_list).split(" ")
            DETAIL -> {
                when (result) {
                    foodList[0] -> foodList = activity.resources.getString(R.string.korean_food_list).split(" ")
                    foodList[1] -> foodList = activity.resources.getString(R.string.western_food_list).split(" ")
                    foodList[2] -> foodList = activity.resources.getString(R.string.chinese_food_list).split(" ")
                    foodList[3] -> foodList = activity.resources.getString(R.string.japanese_food_list).split(" ")
                    foodList[4] -> foodList = listOf("김밥", "라면", "떡볶이", "오므라이스", "덮밥", "만두", "핫도그", "오뎅")
                    foodList[5] -> foodList = listOf("피자", "치킨", "서브웨이", "에그드랍", "햄버거")
                    foodList[6] -> foodList = listOf("닭발", "족발", "보쌈", "막창", "빙수")
                    foodList[7] -> foodList = listOf("쌀국수", "마라탕", "월남쌈", "도시락", "카레")
                }
            }
        }

        for (food in foodList) {
            entry.add(PieEntry(2f, food))
        }

        pieDataSet = PieDataSet(entry, "")
        pieData = PieData(pieDataSet)

        baseRoulette.setPieChart(pieData, pieDataSet, pieChart)
    }

    public fun rotateRoulette(case: String, pieChart: PieChart): String {
        val randomNum = baseRoulette.rotateRoulette(pieChart)

        if (case == CLASSFICATION) {
            return getResult(randomNum)
        } else {
            return ""
        }
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