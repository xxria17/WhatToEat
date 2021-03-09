package com.dohxxn.whattoeat.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dohxxn.whattoeat.Menu
import com.dohxxn.whattoeat.MenuAdapter
import com.dohxxn.whattoeat.R
import com.dohxxn.whattoeat.Roulette.RouletteActivity

class InputActivity : AppCompatActivity() {

    private lateinit var editMenu: EditText
    private lateinit var editBtn: ImageView
    private lateinit var menuList: RecyclerView

    private lateinit var nextButton: TextView
    private lateinit var backButton: ImageView
    private lateinit var scrollView: ScrollView

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
            menu = editMenu.text.toString().trim()
            if (menuArrayList.size >= 20) {
                Toast.makeText(this, "20개 이상 작성할 수 없습니다...", Toast.LENGTH_SHORT).show()
            } else if (!menu.equals("")) {

                menuArrayList.add(Menu(menu))
                adapter = MenuAdapter(menuArrayList)
                menuList.adapter = adapter

                editMenu.setText("")
                menuList.scrollToPosition(adapter.itemCount-2)
            }


        }

        backButton.setOnClickListener {
            finish()
        }

        nextButton.setOnClickListener {
            if (menuArrayList.size == 0) {
                Toast.makeText(this, "내용을 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else if (menuArrayList.size == 1) {
                Toast.makeText(this, "내용을 2개 이상 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@InputActivity, RouletteActivity::class.java)
                intent.putExtra("list", menuArrayList)
                intent.putExtra("current case", "input")
                startActivity(intent)
            }
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

        scrollView = findViewById(R.id.input_scrollView)
    }

}
