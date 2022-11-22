package com.jwiseinc.onedayticket.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jwiseinc.onedayticket.BaseApplication
import com.jwiseinc.onedayticket.R
import com.jwiseinc.onedayticket.model.data.WriteOffRecordDataType
import com.jwiseinc.onedayticket.view.adapter.WriteOffRecordAdapter


class WriteOffRecordFragment : Fragment() {

    lateinit var recycler:RecyclerView
    var mContentView: View? = null
    var writeOffRecordList:ArrayList<WriteOffRecordDataType.WriteOffRecordDataContent> = ArrayList<WriteOffRecordDataType.WriteOffRecordDataContent>()
    var writeOffRecordAdapter:WriteOffRecordAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        mContentView = inflater.inflate(R.layout.fragment_write_off_record, container, false)
        return mContentView
    }

    override fun onResume() {
        super.onResume()
        recycler = mContentView!!.findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this.context)
        writeOffRecordAdapter = WriteOffRecordAdapter(writeOffRecordList,BaseApplication.applicationContext())
        recycler.adapter = writeOffRecordAdapter
    }
}