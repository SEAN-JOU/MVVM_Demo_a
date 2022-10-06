package com.jwiseinc.onedayticket.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jwiseinc.onedayticket.OnCallBack
import com.sray.pigeonmap.model.data.StandardData
import com.sray.pigeonmap.model.data.VersionData
import com.sray.pigeonmap.model.repository.LoginRepository


class LoginViewModel (private val repository: LoginRepository) : ViewModel() {
    val versionData = MutableLiveData<VersionData>()
    val loginData = MutableLiveData<StandardData>()

    fun getVersion(): LiveData<VersionData> {
        repository.getVersion(object : OnCallBack {
            override fun onFinish(data: String) {
                try {
                    val jsonData = Gson().fromJson<VersionData>(data, object : TypeToken<VersionData>() {}.type)
                    versionData.postValue(jsonData)
                }catch (e:Exception){

                }
            }
            override fun onError(data: String) {
            }
        })
        return versionData
    }

    fun login(email:String,password:String): LiveData<StandardData> {
        repository.login(email,password,object : OnCallBack {
            override fun onFinish(data: String) {
                Log.d("" +
                        "",password.trim().toString())
                try {
                    val jsonData = Gson().fromJson<StandardData>(data, object : TypeToken<StandardData>() {}.type)
                    loginData.postValue(jsonData)
                }catch (e:Exception){

                }
            }
            override fun onError(data: String) {
            }
        })
        return loginData
    }
}