package com.dohxxn.whattoeat.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.dohxxn.whattoeat.InfoAdapter
import com.dohxxn.whattoeat.R
import com.dohxxn.whattoeat.Setting

class AppInfoActivity : AppCompatActivity() {

    private lateinit var list : ListView
    private lateinit var clostBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)

        init()

        var setList = arrayListOf<Setting>(
            Setting("도움말"),
            Setting("개발자에게 문의하기"),
            Setting("오픈소스 라이선스"),
            Setting("앱 버전")
        )

        val infoAdapter = InfoAdapter(this, setList)
        list.adapter = infoAdapter

        clostBtn.setOnClickListener { finish() }
    }

    private fun init() {
        list = findViewById(R.id.info_list)
        clostBtn = findViewById(R.id.info_close_btn)
    }
}
