package com.dohxxn.whattoeat.Activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dohxxn.whattoeat.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.util.*

class CalcActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView
    private lateinit var rotateButton: Button
    private lateinit var arrow : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

       init()

        Toast.makeText(this, "핸드폰을 기준으로 둥글게 앉으면 좋아요!", Toast.LENGTH_SHORT).show()

        rotateButton.setOnClickListener {
            val random = Random()
            val randomNum = random.nextInt(360) + 1440
            ObjectAnimator.ofFloat(arrow, View.ROTATION, -360f, (randomNum+100).toFloat()).apply {
                duration = 3000
            }.start()
        }
    }

    private fun init(){
        MobileAds.initialize(this@CalcActivity, getString(R.string.admob_app_id))
        mAdView = findViewById(R.id.calc_adView)
        val adRequest: AdRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        rotateButton = findViewById(R.id.calc_rotate_button)
        arrow = findViewById(R.id.arrow_img)
    }
}
