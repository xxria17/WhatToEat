package com.example.whattoeat.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.whattoeat.R
import com.example.whattoeat.Roulette.RouletteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var goToRandomBtn : View
    private lateinit var goToInputBtn : View
    private lateinit var goToCalBtn: View
    private lateinit var appInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        goToRandomBtn.setOnClickListener {
            startActivity(Intent(this, RouletteActivity::class.java))
        }

        goToInputBtn.setOnClickListener {
            startActivity(Intent(this, InputActivity::class.java))
        }

        goToCalBtn.setOnClickListener {
            startActivity(Intent(this, CalcActivity::class.java))
        }
    }

    private fun init() {
        goToRandomBtn = findViewById(R.id.go_to_random_btn)
        goToInputBtn = findViewById(R.id.go_to_input_btn)
        goToCalBtn = findViewById(R.id.go_to_calc_btn)

        appInfo = findViewById(R.id.app_info)
    }

}