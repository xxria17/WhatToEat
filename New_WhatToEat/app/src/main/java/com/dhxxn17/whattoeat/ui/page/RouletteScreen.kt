package com.dhxxn17.whattoeat.ui.page

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.dhxxn17.whattoeat.R
import com.dhxxn17.whattoeat.ui.theme.blueColor
import com.dhxxn17.whattoeat.ui.theme.greenColor
import com.dhxxn17.whattoeat.ui.theme.redColor
import com.dhxxn17.whattoeat.ui.theme.yellowColor
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

@Composable
fun RouletteScreen(
    navController: NavController
) {
    RouletteContent(navController = navController)
}

@Composable
private fun RouletteContent(navController: NavController) {
    Roulette()
}

@Composable
private fun Roulette() {
    Column(modifier = Modifier.fillMaxWidth()) {
        AndroidView(factory = {context ->
            PieChart(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                description = null
                holeRadius = 0f
                centerText = ""
                isDrawHoleEnabled = false
                setEntryLabelColor(resources.getColor(R.color.black))
                setEntryLabelTextSize(16f)
                legend.isEnabled = false
                invalidate()
            }
        },
            modifier = Modifier
                .wrapContentSize()
                .padding(5.dp),
            update = {
                updatePieChartWithData(it, getPieChartData)
            }
        )
    }
}

private fun updatePieChartWithData(
    chart: PieChart,
    data: List<PieChartData>
) {
    val entries = ArrayList<PieEntry>()
    for (i in data.indices) {
        val item = data[i]
        entries.add(PieEntry(item.value ?: 0.toFloat(), item.browserName ?: ""))
    }

    val ds = PieDataSet(entries, "")

    ds.colors = arrayListOf(
        greenColor.toArgb(),
        redColor.toArgb(),
        blueColor.toArgb(),
        yellowColor.toArgb()
    )

    ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
    ds.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

    ds.sliceSpace = 2f

//    ds.valueTextColor = resources.getColor(R.color.white)
    ds.valueTextSize = 18f
    ds.valueTypeface = Typeface.DEFAULT_BOLD

    val d = PieData(ds)
    chart.data = d

    chart.invalidate()
}

data class PieChartData(
    var browserName: String?,
    var value: Float?
)

val getPieChartData = listOf(
    PieChartData("Chrome", 34.68f),
    PieChartData("Firefox", 16.60f),
    PieChartData("Safari", 16.15f),
    PieChartData("Internet Explorer", 15.62f),

)