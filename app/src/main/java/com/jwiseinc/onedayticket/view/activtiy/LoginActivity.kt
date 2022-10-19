package com.jwiseinc.onedayticket.view.activtiy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jwiseinc.onedayticket.R
import com.jwiseinc.onedayticket.utils.MD5Util
import com.jwiseinc.onedayticket.viewmodel.LoginViewModel
import com.sray.pigeonmap.model.factory.LoginViewModelFactory
import com.sray.pigeonmap.model.repository.LoginRepository
import com.sray.pigeonmap.utils.ExampleUtil
import com.sray.pigeonmap.utils.SharedPreferencesUtil

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginViewModel
    lateinit var loginBtn:Button
    lateinit var memberIDEdt:EditText
    lateinit var passwordEdt:EditText
    lateinit var versionTxt:TextView
    lateinit var forgetPasswordBtn:TextView
    lateinit var rememberCheckBox:CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        rememberCheckBox = findViewById(R.id.remember_checkBox)
        loginBtn = findViewById(R.id.login_btn)
        passwordEdt = findViewById(R.id.password_edt)
        memberIDEdt = findViewById(R.id.memberID_edt)
        versionTxt = findViewById(R.id.version)
        forgetPasswordBtn = findViewById(R.id.forget_password)

        versionTxt.text = "v"+ExampleUtil.getLocalVersionName(this)
        viewModel = ViewModelProvider(this, LoginViewModelFactory(LoginRepository())).get(
            LoginViewModel::class.java
        )

        if(SharedPreferencesUtil.getKeyValue("password",this) != null && SharedPreferencesUtil.getKeyValue("password",this) != ""){
            rememberCheckBox.isChecked = true
            passwordEdt.setText(SharedPreferencesUtil.getKeyValue("password",this))
        }

        if(SharedPreferencesUtil.getKeyValue("memberID",this) != null && SharedPreferencesUtil.getKeyValue("memberID",this) != ""){
            memberIDEdt.setText(SharedPreferencesUtil.getKeyValue("memberID",this))
        }

        rememberCheckBox.setOnClickListener {
            if(!rememberCheckBox.isChecked){
                SharedPreferencesUtil.setKeyValue("password","",this)
                passwordEdt.setText("")
            }
        }

        loginBtn.setOnClickListener{
            if(passwordEdt.text.toString() != null && passwordEdt.text.toString() != "" && memberIDEdt.text.toString() != null && memberIDEdt.text.toString() != ""){
                viewModel.login(memberIDEdt.text.toString(), MD5Util.md5(memberIDEdt.text.toString()+passwordEdt.text.toString()))
                    .observe(this, Observer {
                        if(it.sysCode!! >= 0){
                            val it = Intent(this, MainActivity::class.java)
                            startActivity(it)
                        }else{

                        }
                    })
                SharedPreferencesUtil.setKeyValue("memberID",memberIDEdt.text.toString(),this)
                if(rememberCheckBox.isChecked){
                    SharedPreferencesUtil.setKeyValue("password",passwordEdt.text.toString(),this)
                }
            }else{
                Toast.makeText(this,"輸入框不得為空",Toast.LENGTH_SHORT).show()
            }
        }

        forgetPasswordBtn.setOnClickListener{
            val it = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(it)
        }
    }
}