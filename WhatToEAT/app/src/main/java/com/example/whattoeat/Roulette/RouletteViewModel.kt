package com.example.whattoeat.Roulette

import android.app.Activity
import android.util.Log
import com.example.whattoeat.Menu
import com.example.whattoeat.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class RouletteViewModel {

    private val baseRoulette = BaseRoulette()
    private lateinit var pieData: PieData
    private lateinit var pieDataSet: PieDataSet

    private val CLASSFICATION: String = "Classification"
    private val DETAIL : String = "Detail"
    private val INPUT: String = "Input"

    public fun setChartUI(data: String, activity: Activity, pieChart: PieChart, result: String, list: ArrayList<Menu>?) {
        val entry = mutableListOf<PieEntry>()
        var rouletteList = ArrayList<String>()
        when (data) {
            CLASSFICATION -> {
                rouletteList = activity.resources.getString(R.string.food_list).split(" ") as ArrayList<String>
            }

            DETAIL -> {
                when (result) {
                    "한식" -> rouletteList = activity.resources.getString(R.string.korean_food_list).split(" ") as ArrayList<String>
                    "양식" -> rouletteList = activity.resources.getString(R.string.western_food_list).split(" ") as ArrayList<String>
                    "중식" -> rouletteList = activity.resources.getString(R.string.chinese_food_list).split(" ") as ArrayList<String>
                    "일식" -> rouletteList = activity.resources.getString(R.string.japanese_food_list).split(" ") as ArrayList<String>
                    "분식" -> rouletteList = activity.resources.getString(R.string.bunsik_food_list).split(" ") as ArrayList<String>
                    "패스트푸드" -> rouletteList = activity.resources.getString(R.string.fast_food_list).split(" ") as ArrayList<String>
                    "야식" -> rouletteList = activity.resources.getString(R.string.night_food_list).split(" ") as ArrayList<String>
                    "기타" -> rouletteList = activity.resources.getString(R.string.etc_food_list).split(" ") as ArrayList<String>
                }
            }
            INPUT -> {
                if (list != null) {
                    for (item in list) {
                        rouletteList.add(item.menu)
                    }
                }
            }
        }

        for (food in rouletteList) {
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