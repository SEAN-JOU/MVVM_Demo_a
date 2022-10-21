package com.jwiseinc.onedayticket.view.activtiy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jwiseinc.onedayticket.R
import com.jwiseinc.onedayticket.element.AVLoadingIndicatorView

abstract class BaseActivity: AppCompatActivity() {

    companion object {
        lateinit var loadingView: AVLoadingIndicatorView
    }
    protected abstract fun setLayoutViewId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutViewId())
        loadingView = findViewById(R.id.loadingView)
    }

    override fun onBackPressed() {
    }
}