package com.jwiseinc.onedayticket.view.activtiy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jwiseinc.onedayticket.R
import com.jwiseinc.onedayticket.model.repository.ForgetPasswordRepository
import com.jwiseinc.onedayticket.utils.XClickUtil
import com.jwiseinc.onedayticket.viewmodel.ForgetPasswordViewModel
import com.sray.pigeonmap.model.factory.ForgetPasswordViewModelFactory
import com.sray.pigeonmap.utils.SharedPreferencesUtil

class ForgetPasswordActivity : BaseActivity() {

    lateinit var backBtn:ImageView
    lateinit var sendBtn:Button
    lateinit var viewModel: ForgetPasswordViewModel
    lateinit var memberIDEdt:EditText
    lateinit var emailEdt:EditText

    override fun setLayoutViewId(): Int {
        return R.layout.activity_forget_password
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ForgetPasswordViewModelFactory(ForgetPasswordRepository())).get(
            ForgetPasswordViewModel::class.java
        )

        memberIDEdt = findViewById(R.id.memberID_edt)
        emailEdt = findViewById(R.id.email_edt)
        backBtn = findViewById(R.id.backBtn)
        sendBtn = findViewById(R.id.sendBtn)

        sendBtn.setOnClickListener {
            if (!XClickUtil.isFastDoubleClick(sendBtn)) {
                if (emailEdt.text != null && emailEdt.text.toString() != "" &&
                    memberIDEdt.text != null && memberIDEdt.text.toString() != ""
                ) {
                    loadingView.show()
                    SharedPreferencesUtil.setKeyValue("memberID", memberIDEdt.text.toString(), this)
                    viewModel.forget(memberIDEdt.text.toString(), emailEdt.text.toString())
                        .observe(this,
                            {
                                loadingView.hide()
                                if (it!!.sysCode!! >= 0) {
                                    SharedPreferencesUtil.setKeyValue("memberID", memberIDEdt.text.toString(), this)
                                    val intent = Intent(this, MotifyPasswordActivity::class.java)
                                    intent.putExtra("authcode", it.data!!.authcode);
                                    startActivity(intent)
                                } else {
                                    when (it!!.sysCode!!) {
                                                -1 -> Toast.makeText(this, "帳號不存在", Toast.LENGTH_SHORT).show()
                                        -2 -> Toast.makeText(this, "帳號密碼不正確", Toast.LENGTH_SHORT).show()
                                        -3 -> Toast.makeText(this, "帳號未啟用", Toast.LENGTH_SHORT).show()
                                        -4 -> Toast.makeText(this, "帳號被系統管理者鎖定", Toast.LENGTH_SHORT)
                                            .show()
                                        -5 -> Toast.makeText(this, "找不到對應的會員資料", Toast.LENGTH_SHORT)
                                            .show()
                                        -6 -> Toast.makeText(this, "會員未啟用", Toast.LENGTH_SHORT).show()
                                        -7 -> Toast.makeText(this, "會員已被系統管理者鎖定", Toast.LENGTH_SHORT)
                                            .show()
                                        -8 -> Toast.makeText(this, "註冊信箱不正確", Toast.LENGTH_SHORT)
                                            .show()
                                        else -> Toast.makeText(this, "發生不明錯誤", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                                if (it != null) {
                                    viewModel.forgetPasswordData.removeObservers(this)
                                }
                            })

                } else {
                    Toast.makeText(this, "輸入框不得為空", Toast.LENGTH_SHORT).show()
                }
            }
        }
        backBtn.setOnClickListener{
            finish()
        }
    }
}