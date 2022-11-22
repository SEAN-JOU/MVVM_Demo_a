package com.jwiseinc.onedayticket.view.activtiy

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.jwiseinc.onedayticket.R


class WriteOffActivity : BaseActivity() {

    lateinit var writeOffButton:TextView
    lateinit var backBtn: ImageView


    override fun setLayoutViewId(): Int {
        return R.layout.activity_write_off
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backBtn = findViewById(R.id.backBtn)
        writeOffButton = findViewById(R.id.writeOffButton)

        backBtn.setOnClickListener {
            val intent = Intent(this@WriteOffActivity, ScannerActivity::class.java)
            startActivity(intent)
        }
        val v: View = LayoutInflater.from(this@WriteOffActivity).inflate(R.layout.success_dialog, null)
        writeOffButton.setOnClickListener {
            var alertDialog1:AlertDialog.Builder = AlertDialog.Builder(this@WriteOffActivity)
                .setTitle("請確認是否核銷選取的項目?")
            alertDialog1.setPositiveButton("確定") { _, _ ->
                var alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@WriteOffActivity)
                    .setTitle("")
                    .setView(v)
                var confirmButton:Button = v.findViewById<Button>(R.id.confirm)
                confirmButton.setOnClickListener {
                    val intent = Intent(this, ScannerActivity::class.java)
                    startActivity(intent)
                }
                alertDialog.show()
            }.setNeutralButton("取消") { _, _ ->

            }
            alertDialog1.show()
        }
    }
}