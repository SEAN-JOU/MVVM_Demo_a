package com.jwiseinc.onedayticket.view.activtiy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.jwiseinc.onedayticket.R
import com.jwiseinc.onedayticket.element.NoSlideViewPager

//https://givemepass.blogspot.com/2019/04/tablayout-viewpager.html

class MainActivity : BaseActivity() {

    lateinit var mTabLayout: TabLayout
    lateinit var viewpager:NoSlideViewPager
    lateinit var qrcodeBtn:FloatingActionButton

    override fun setLayoutViewId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        qrcodeBtn = findViewById(R.id.qrcodeBtn)
        mTabLayout = findViewById(R.id.tablayout)
        viewpager = findViewById(R.id.viewpager)
        loadingView.show()
        qrcodeBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, ScannerActivity::class.java)
            startActivity(intent)
        }
    }
}