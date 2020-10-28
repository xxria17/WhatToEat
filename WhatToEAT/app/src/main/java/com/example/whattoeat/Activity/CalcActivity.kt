package com.example.whattoeat.Activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whattoeat.R
import java.util.*

class CalcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        val rotateButton: Button = findViewById(R.id.calc_rotate_button)
        val arrow: ImageView = findViewById(R.id.arrow_img)

        Toast.makeText(this, "핸드폰을 기준으로 둥글게 앉으면 좋아요!", Toast.LENGTH_SHORT).show()

        rotateButton.setOnClickListener {
            val random = Random()
            val randomNum = random.nextInt(360) + 1440
            ObjectAnimator.ofFloat(arrow, View.ROTATION, -360f, (randomNum+100).toFloat()).apply {
                duration = 3000
            }.start()
        }
    }
}
