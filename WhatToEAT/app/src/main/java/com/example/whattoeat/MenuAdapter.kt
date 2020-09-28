package com.example.whattoeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private var menuList: ArrayList<Menu>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val numberText: TextView = itemView.findViewById(R.id.item_numbering)
        val menuText: TextView = itemView.findViewById(R.id.item_menu)
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
//        mViewHolder.numberText.text = (position + 1).toString()
        mViewHolder.menuText.text = menuList[position].menu
    }
}