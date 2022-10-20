package com.jwiseinc.onedayticket.view.activtiy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jwiseinc.onedayticket.R
import com.jwiseinc.onedayticket.model.repository.ForgetPasswordRepository
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

        sendBtn.setOnClickListener{
            if(emailEdt.text != null && emailEdt.text.toString() != "" &&
                    memberIDEdt.text != null && memberIDEdt.text.toString() != "" ){
                    loadingView.show()
                    SharedPreferencesUtil.setKeyValue("memberID",memberIDEdt.text.toString(),this)
                    viewModel.forget(memberIDEdt.text.toString(),emailEdt.text.toString()).observe(this,
                        {
                        if(it!!.sysCode!! >= 0){
                            val intent = Intent(this, MotifyPasswordActivity::class.java)
                            startActivity(intent)
                        }else{
                        }
                            if(it != null){
                                viewModel.forgetPasswordData.removeObservers(this)
                            }
                    })

                }else{
                    Toast.makeText(this,"輸入框不得為空",Toast.LENGTH_SHORT).show()
                }
        }
        backBtn.setOnClickListener{
            finish()
        }
    }
}