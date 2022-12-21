package com.jwiseinc.onedayticket.view.activtiy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jwiseinc.onedayticket.ExampleUtil
import com.jwiseinc.onedayticket.R
import com.jwiseinc.onedayticket.utils.MD5Util
import com.jwiseinc.onedayticket.utils.XClickUtil
import com.jwiseinc.onedayticket.viewmodel.LoginViewModel
import com.sray.pigeonmap.model.factory.LoginViewModelFactory
import com.jwiseinc.onedayticket.model.repository.LoginRepository
import com.sray.pigeonmap.utils.SharedPreferencesUtil

class LoginActivity : BaseActivity() {

    lateinit var viewModel: LoginViewModel
    lateinit var loginBtn:Button
    lateinit var memberIDEdt:EditText
    lateinit var passwordEdt:EditText
    lateinit var versionTxt:TextView
    lateinit var forgetPasswordBtn:TextView
    lateinit var rememberCheckBox:CheckBox

    override fun setLayoutViewId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rememberCheckBox = findViewById(R.id.remember_checkBox)
        loginBtn = findViewById(R.id.login_btn)
        passwordEdt = findViewById(R.id.password_edt)
        memberIDEdt = findViewById(R.id.memberID_edt)
        versionTxt = findViewById(R.id.version)
        forgetPasswordBtn = findViewById(R.id.forget_password)

        versionTxt.text = "v"+ ExampleUtil.getLocalVersionName(this)
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
//          Toast.makeText(this,"版本資料檢查中，請稍候。",Toast.LENGTH_SHORT).show()
            viewModel.login(
                memberIDEdt.text.toString(),
                MD5Util.md5(memberIDEdt.text.toString() + passwordEdt.text.toString())
            ).observe(this, Observer {
                loadingView.hide()
                if (it!!.sysCode!! >= 0) {
                    SharedPreferencesUtil.setKeyValue("session",it!!.data!!.session_id!!,this)
                    val intent = Intent(this, MainActivity::class.java)
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
                        else -> Toast.makeText(this, "發生不明錯誤", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                if(it != null){
                    viewModel.loginData.removeObservers(this)
                }
            })
        }

//        viewModel.getVersion().observe(this,{
//            loginBtn.setOnClickListener{
//                if(!XClickUtil.isFastDoubleClick(loginBtn)){
//                    if (passwordEdt.text.toString() != null && passwordEdt.text.toString() != "" && memberIDEdt.text.toString() != null && memberIDEdt.text.toString() != "") {
//                        loadingView.show()
//
////                        viewModel.login(
////                            memberIDEdt.text.toString(),
////                            MD5Util.md5(memberIDEdt.text.toString() + passwordEdt.text.toString())
////                        ).observe(this, Observer {
////                            loadingView.hide()
////                            if (it!!.sysCode!! >= 0) {
////                                SharedPreferencesUtil.setKeyValue("session",it!!.data!!.session_id!!,this)
////                                val intent = Intent(this, MainActivity::class.java)
////                                startActivity(intent)
////                            } else {
////                                when (it!!.sysCode!!) {
////                                    -1 -> Toast.makeText(this, "帳號不存在", Toast.LENGTH_SHORT).show()
////                                    -2 -> Toast.makeText(this, "帳號密碼不正確", Toast.LENGTH_SHORT).show()
////                                    -3 -> Toast.makeText(this, "帳號未啟用", Toast.LENGTH_SHORT).show()
////                                    -4 -> Toast.makeText(this, "帳號被系統管理者鎖定", Toast.LENGTH_SHORT)
////                                        .show()
////                                    -5 -> Toast.makeText(this, "找不到對應的會員資料", Toast.LENGTH_SHORT)
////                                        .show()
////                                    -6 -> Toast.makeText(this, "會員未啟用", Toast.LENGTH_SHORT).show()
////                                    -7 -> Toast.makeText(this, "會員已被系統管理者鎖定", Toast.LENGTH_SHORT)
////                                        .show()
////                                    else -> Toast.makeText(this, "發生不明錯誤", Toast.LENGTH_SHORT)
////                                        .show()
////                                }
////                            }
////                            if(it != null){
////                                viewModel.loginData.removeObservers(this)
////                            }
////                        })
//                        SharedPreferencesUtil.setKeyValue("memberID", memberIDEdt.text.toString(), this)
//                        if (rememberCheckBox.isChecked) {
//                            SharedPreferencesUtil.setKeyValue(
//                                "password",
//                                passwordEdt.text.toString(),
//                                this
//                            )
//                        }
//                    } else {
//                        Toast.makeText(this, "輸入框不得為空", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//            if (it!!.sysCode!! >= 0) {
//                if(ExampleUtil.compareVersion(
//                        ExampleUtil.getLocalVersionName(this)!!,
//                        it!!.data!!.android_version!!
//                    )!!){
//                    if(it!!.data!!.android_need_update!!){
//                        AlertDialog.Builder(this)
//                            .setIcon(R.drawable.logo)
//                            .setMessage("前往Google Play更新Oride套票管理平台")
//                            .setTitle("版本更新")
//                            .setPositiveButton("更新",  {
//                                    dialog, id ->  val googleplay = Intent(Intent.ACTION_VIEW)
//                                googleplay.data = Uri.parse(it.data?.android_app_path)
//                                startActivity(googleplay)
//                            })
//                            .show()
//                    }else{
//                        AlertDialog.Builder(this)
//                            .setIcon(R.drawable.logo)
//                            .setMessage("前往Google Play更新Oride套票管理平台")
//                            .setTitle("版本更新")
//                            .setPositiveButton("更新",  {
//                                    dialog, id ->
//                                val googleplay = Intent(Intent.ACTION_VIEW)
//                                googleplay.data = Uri.parse(it.data?.android_app_path)
//                                startActivity(googleplay)
//                            })
//                            .setNegativeButton("取消",{
//                                    dialog, id ->
//                            })
//                            .show()
//                    }
//                }
//            }else{
//            }
//        })
        forgetPasswordBtn.setOnClickListener{
            val it = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(it)
        }
    }
}