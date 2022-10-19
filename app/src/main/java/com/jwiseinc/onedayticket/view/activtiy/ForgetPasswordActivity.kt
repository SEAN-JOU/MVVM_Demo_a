package com.jwiseinc.onedayticket.view.activtiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.jwiseinc.onedayticket.R
import com.jwiseinc.onedayticket.loadingview.AVLoadingIndicatorView

class ForgetPasswordActivity : AppCompatActivity() {

    lateinit var backBtn:ImageView
    lateinit var sendBtn:Button
    lateinit var loadingView:AVLoadingIndicatorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        loadingView = findViewById(R.id.loadingView)

        backBtn = findViewById(R.id.backBtn)
        sendBtn = findViewById(R.id.sendBtn)

        sendBtn.setOnClickListener{
            loadingView.show()
        }
        backBtn.setOnClickListener{
            finish()
        }
    }
}