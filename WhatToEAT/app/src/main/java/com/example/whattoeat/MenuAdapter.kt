package com.example.whattoeat

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private var menuList: ArrayList<Menu>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuText: TextView = itemView.findViewById(R.id.item_menu)
        val menuView: View = itemView.findViewById(R.id.input_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_input, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mViewHolder: ViewHolder = holder as ViewHolder
        val baseColor = BaseColor()
        val chartColor = baseColor.CHART_COLOR.toMutableList()
        mViewHolder.menuText.text = menuList[position].menu
        mViewHolder.menuView.backgroundTintList = ColorStateList.valueOf(chartColor[position])
    }
}