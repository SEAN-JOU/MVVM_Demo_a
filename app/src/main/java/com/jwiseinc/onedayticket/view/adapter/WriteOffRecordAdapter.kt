package com.jwiseinc.onedayticket.view.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jwiseinc.onedayticket.R
import com.jwiseinc.onedayticket.model.data.WriteOffRecordDataType


class WriteOffRecordAdapter (val writeOffRecordDatas: ArrayList<WriteOffRecordDataType.WriteOffRecordDataContent>, var context: Context) : RecyclerView.Adapter<WriteOffRecordAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.writeoffrecord_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = writeOffRecordDatas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var writeOffRecordData = writeOffRecordDatas.get(position)
    }

    class ViewHolder(val view: View) :  RecyclerView.ViewHolder(view) {

    }
}
