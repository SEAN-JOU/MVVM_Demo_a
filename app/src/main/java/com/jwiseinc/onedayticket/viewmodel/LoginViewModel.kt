package com.jwiseinc.onedayticket.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jwiseinc.onedayticket.OnCallBack
import com.jwiseinc.onedayticket.SingleLiveData
import com.jwiseinc.onedayticket.view.activtiy.BaseActivity
import com.sray.pigeonmap.model.data.LoginDataType
import com.sray.pigeonmap.model.repository.LoginRepository


class LoginViewModel (private val repository: LoginRepository) : ViewModel() {

    val loginData = SingleLiveData<LoginDataType>()

    fun login(memberID:String,password:String): SingleLiveData<LoginDataType> {
        repository.login(memberID,password,object : OnCallBack {
            override fun onFinish(data: String) {
                try {
                    val jsonData = Gson().fromJson<LoginDataType>(data, object : TypeToken<LoginDataType>() {}.type)
                    loginData.postValue(jsonData)
                }catch (e:Exception){
                    BaseActivity.loadingView.hide()
                }
            }
            override fun onError(data: String) {
                BaseActivity.loadingView.hide()
            }
        })
        return loginData
    }
}