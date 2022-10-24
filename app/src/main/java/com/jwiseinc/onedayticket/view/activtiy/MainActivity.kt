package com.jwiseinc.onedayticket.view.activtiy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.jwiseinc.onedayticket.R
import com.jwiseinc.onedayticket.element.NoSlideViewPager
import com.jwiseinc.onedayticket.view.adapter.MainAdapter
import com.jwiseinc.onedayticket.view.fragment.PackageInfoFragment
import com.jwiseinc.onedayticket.view.fragment.WriteOffRecordFragment

//https://givemepass.blogspot.com/2019/04/tablayout-viewpager.html
//https://www.javatpoint.com/kotlin-android-tablayout-with-viewpager

class MainActivity : BaseActivity() {

    lateinit var mTabLayout: TabLayout
    lateinit var mViewPager:NoSlideViewPager
    lateinit var qrcodeBtn:ImageView
    lateinit var adapter: MainAdapter
    lateinit var text1:TextView
    lateinit var text2:TextView
    private val sOffScreenLimit = 1

    override fun setLayoutViewId(): Int {
        return R.layout.activity_main
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        qrcodeBtn = findViewById(R.id.qrcodeBtn)
        mTabLayout = findViewById(R.id.tablayout)
        mViewPager = findViewById(R.id.viewpager)
        loadingView.show()

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(PackageInfoFragment())
        fragmentList.add(WriteOffRecordFragment())

        val titleList = ArrayList<String>()
        for (i in 0..4) {
            titleList.add("Page_$i")
        }

        adapter = MainAdapter(supportFragmentManager, fragmentList)
        mViewPager.setAdapter(adapter)
        mTabLayout.setupWithViewPager(mViewPager)
        if (sOffScreenLimit > 1) {
            mViewPager.setOffscreenPageLimit(sOffScreenLimit)
        }

        val headerView = (getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.custom_tab, null, false)
        val linearLayout1 = headerView.findViewById<View>(R.id.ll) as LinearLayout
        val linearLayout2 = headerView.findViewById<View>(R.id.ll2) as LinearLayout
        text1 = linearLayout1.findViewById<View>(R.id.text1) as TextView
        text2 = linearLayout2.findViewById<View>(R.id.text2) as TextView

        text1.setTextColor(getColor(R.color.colorAccent))
        mTabLayout.getTabAt(0)!!.customView = linearLayout1
        mTabLayout.getTabAt(1)!!.customView = linearLayout2

        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab!!.position.toString() == "0") {
                        text1.setTextColor(getColor(R.color.colorAccent))
                    } else if (tab!!.position.toString() == "1") {
                        text2.setTextColor(getColor(R.color.colorAccent))
                    }
            }
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onTabUnselected(p0: TabLayout.Tab?) {
                    text1.setTextColor(getColor(R.color.gray_3))
                    text2.setTextColor(getColor(R.color.gray_3))
            }})

        qrcodeBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, ScannerActivity::class.java)
            startActivity(intent)
        }
    }
}