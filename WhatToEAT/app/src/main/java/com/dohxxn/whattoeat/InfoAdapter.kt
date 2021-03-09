package com.dohxxn.whattoeat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class InfoAdapter(val context: Context, val setList: ArrayList<Setting>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_setting, null)

        val set = view.findViewById<TextView>(R.id.item_set_tv)
        set.text = setList[position].set
        return view
    }

    override fun getItem(position: Int): Any {
        return setList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return setList.size
    }

}