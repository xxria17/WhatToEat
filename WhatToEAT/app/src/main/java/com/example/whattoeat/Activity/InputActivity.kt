package com.example.whattoeat.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattoeat.Menu
import com.example.whattoeat.MenuAdapter
import com.example.whattoeat.R
import com.example.whattoeat.Roulette.RouletteActivity

class InputActivity : AppCompatActivity() {

    private lateinit var editMenu: EditText
    private lateinit var editBtn: Button
    private lateinit var menuList: RecyclerView

    private lateinit var nextButton: TextView
    private lateinit var backButton: ImageView

    private lateinit var menuArrayList: ArrayList<Menu>

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        init()

        var menu: String = ""
        menuArrayList = ArrayList<Menu>()

        editBtn.setOnClickListener {
            menu = editMenu.text.toString()
            menuArrayList.add(Menu(menu))
            adapter = MenuAdapter(menuArrayList)
            menuList.adapter = adapter

            editMenu.setText("")
        }

        backButton.setOnClickListener {
            finish()
        }

        nextButton.setOnClickListener {
            val intent = Intent(this@InputActivity, RouletteActivity::class.java)
            intent.putExtra("list", menuArrayList)
            intent.putExtra("current case", "input")
            startActivity(intent)
        }
    }

    private fun init() {
        editMenu = findViewById(R.id.edit_menu)
        editBtn = findViewById(R.id.edit_button)
        menuList = findViewById(R.id.menu_list)

        layoutManager = LinearLayoutManager(this)
        menuList.layoutManager = layoutManager

        nextButton = findViewById(R.id.input_next_btn)
        backButton = findViewById(R.id.input_back_btn)
    }
}
