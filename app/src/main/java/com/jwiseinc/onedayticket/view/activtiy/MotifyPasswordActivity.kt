package com.jwiseinc.onedayticket.view.activtiy

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jwiseinc.onedayticket.R
import com.jwiseinc.onedayticket.model.factory.MotifyPasswordViewModelFactory
import com.jwiseinc.onedayticket.model.repository.MotifyPasswordRepository
import com.jwiseinc.onedayticket.utils.XClickUtil
import com.jwiseinc.onedayticket.viewmodel.MotifyPasswordViewModel


class MotifyPasswordActivity : BaseActivity() {

    lateinit var viewModel: MotifyPasswordViewModel
    lateinit var motifyBtn: Button
    lateinit var backBtn: ImageView
    lateinit var randNumberEdt: EditText
    lateinit var newPasswordEdt: EditText
    lateinit var againPasswordEdt: EditText
    var authcode: String = ""

    override fun setLayoutViewId(): Int {
        return R.layout.activity_motify_password
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.extras?.getString("authcode") != null) {
            authcode = intent.extras?.getString("authcode")!!
        }
        backBtn = findViewById(R.id.backBtn)
        randNumberEdt = findViewById(R.id.randNumber_edt)
        newPasswordEdt = findViewById(R.id.newPassword_edt)
        againPasswordEdt = findViewById(R.id.againPassword_edt)
        motifyBtn = findViewById(R.id.motifyBtn)

        viewModel =
            ViewModelProvider(this, MotifyPasswordViewModelFactory(MotifyPasswordRepository())).get(
                MotifyPasswordViewModel::class.java
            )
        backBtn.setOnClickListener {
            finish()
        }
        motifyBtn.setOnClickListener {
            if (!XClickUtil.isFastDoubleClick(motifyBtn)) {
                if (randNumberEdt.text.toString() != null && randNumberEdt.text.toString() != ""
                    && newPasswordEdt.text.toString() != null && newPasswordEdt.text.toString() != ""
                    && againPasswordEdt.text.toString() != null && againPasswordEdt.text.toString() != ""
                ) {
                    if (newPasswordEdt.text.toString().length >= 6) {
                        if (newPasswordEdt.text.toString().length <= 12) {
                            if (newPasswordEdt.text.toString() == againPasswordEdt.text.toString()) {
                                loadingView.show()
                                viewModel.resetPassword(
                                    authcode,
                                    newPasswordEdt.text.toString(),
                                    randNumberEdt.text.toString()
                                ).observe(this, Observer {
                                    loadingView.hide()
                                    if (it!!.sysCode!! >= 0) {
                                        AlertDialog.Builder(this)
                                            .setTitle("修改成功")
                                            .setCancelable(false)
                                            .setPositiveButton("返回登入頁",  {
                                                    dialog, id ->
                                                val intent = Intent(this@MotifyPasswordActivity, LoginActivity::class.java)
                                                startActivity(intent)
                                            })
                                            .show()
                                    } else {
                                        when (it!!.sysCode!!) {
                                            -1 -> Toast.makeText(this, "未登入或session已過期", Toast.LENGTH_SHORT).show()
                                            -2 -> Toast.makeText(this, "商店被停用", Toast.LENGTH_SHORT).show()
                                            -3 -> Toast.makeText(this, "帳號被系統管理者鎖定", Toast.LENGTH_SHORT).show()
                                            -14 -> Toast.makeText(this, "驗證碼已使用", Toast.LENGTH_SHORT)
                                                .show()
                                            -15 -> Toast.makeText(this, "驗證碼錯誤", Toast.LENGTH_SHORT)
                                                .show()
                                            else -> Toast.makeText(this, "發生不明錯誤", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    }
                                    if(it != null){
                                        viewModel.motifyPasswordDataType.removeObservers(this)
                                    }
                                })
                            } else {
                                Toast.makeText(this, "再次輸入密碼與新密碼不一致", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, "新密碼長度超過12個字符", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "新密碼長度不足6個字符", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "輸入框不得為空", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}