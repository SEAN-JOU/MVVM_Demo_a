package com.jwiseinc.onedayticket.view.activtiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.jwiseinc.onedayticket.R

class ForgetPasswordActivity : AppCompatActivity() {

    lateinit var backBtn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        backBtn = findViewById(R.id.backBtn)
        backBtn.setOnClickListener{
            finish()
        }
    }
}