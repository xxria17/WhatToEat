# What-To_Eat

오늘 뭐 먹을지 룰렛을 돌려서 랜덤으로 정하는 안드로이드 어플리케이션 입니다. (Kotlin)

#### 캡쳐화면
![capture_1](https://user-images.githubusercontent.com/41279544/87512093-64bf6f80-c6b1-11ea-9f60-b7bfb6033751.jpg)
![capture_2](https://user-images.githubusercontent.com/41279544/87512136-73a62200-c6b1-11ea-83dd-57e3877f94be.jpg)
![capture_3](https://user-images.githubusercontent.com/41279544/87512140-743eb880-c6b1-11ea-951e-af46fc3963ab.jpg)


#### 룰렛 사용
https://github.com/PhilJay/MPAndroidChart  에서 PieChart 사용

**룰렛 설정**
```Kotlin
pieDataSet = PieDataSet(entry, "")
pieData = PieData(pieDataSet)
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
```

**룰렛 돌리기**
```Kotlin
val random = Random()
val randomNum = random.nextInt(360) + 1440
        
pieChart.spin(1000, 0f, -randomNum.toFloat(), Easing.EaseInOutQuad)
```
