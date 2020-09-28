package com.example.whattoeat.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.whattoeat.R

class CalcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        val rotateButton: Button = findViewById(R.id.calc_rotate_button)
        val arrow: ImageView = findViewById(R.id.arrow_img)

        Toast.makeText(this, "핸드폰을 기준으로 둥글게 앉으면 좋아요!", Toast.LENGTH_SHORT).show()

//        rotateButton.setOnClickListener {
//            arrow.animation =
//        }
    }
}
