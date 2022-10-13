package com.jwiseinc.onedayticket.view.activtiy

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.jwiseinc.onedayticket.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        object : CountDownTimer(2000, 1000) {
            override fun onFinish() {
                finish()
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            override fun onTick(millisUntilFinished: Long) {
            }
        }.start()
    }
}