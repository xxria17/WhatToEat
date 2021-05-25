# 뭐먹양

## 서비스 소개
> 오늘 뭐 먹을지 룰렛을 돌려서 랜덤으로 정하는 안드로이드 어플리케이션.

<br>

## 캡쳐화면
<img src = "https://user-images.githubusercontent.com/41279544/119465400-96e43800-bd7e-11eb-9af1-103dadbad7d0.jpg" width="300" height="720"><img src = "https://user-images.githubusercontent.com/41279544/119465437-9fd50980-bd7e-11eb-8298-0cabe10f0377.jpg" width="300" height="720">

<img src = "https://user-images.githubusercontent.com/41279544/119465446-a19ecd00-bd7e-11eb-91a3-87e8d0cd47b0.jpg" width="300" height="720"><img src = "https://user-images.githubusercontent.com/41279544/119465421-9c418280-bd7e-11eb-9668-5b1d20d1c4b1.jpg" width="300" height="720">

<br>

## 사용된 룰렛

https://github.com/PhilJay/MPAndroidChart  에서 PieChart 사용
룰렛의 검정색 핀은 개별적인 이미지뷰입니다. 

<br>

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

<br>

## 플레이스토어 
https://play.google.com/store/apps/details?id=com.dohxxn.whattoeat
