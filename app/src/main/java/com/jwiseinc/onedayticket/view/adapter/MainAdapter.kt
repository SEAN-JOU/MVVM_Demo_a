package com.jwiseinc.onedayticket.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainAdapter(fm: FragmentManager?, var fragmentList: List<Fragment>?) :
    FragmentPagerAdapter(fm!!) {
    override fun getCount(): Int {
        return if (fragmentList != null) fragmentList!!.size else 0
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }
}