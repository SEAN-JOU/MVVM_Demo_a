package com.jwiseinc.onedayticket.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jwiseinc.onedayticket.OnCallBack
import com.jwiseinc.onedayticket.SingleLiveData
import com.jwiseinc.onedayticket.model.data.LoginDataType
import com.jwiseinc.onedayticket.model.data.VersionDataType
import com.jwiseinc.onedayticket.model.repository.LoginRepository


class LoginViewModel (private val repository: LoginRepository) : ViewModel() {

    val loginData = SingleLiveData<LoginDataType>()
    val versionData = SingleLiveData<VersionDataType>()

    fun login(memberID:String,password:String): SingleLiveData<LoginDataType> {
        repository.login(memberID,password,object : OnCallBack {
            override fun onFinish(data: String) {
                try {
                    Log.d("test_callback", data)
                    val jsonData = Gson().fromJson<LoginDataType>(data, object : TypeToken<LoginDataType>() {}.type)
                    loginData.postValue(jsonData)
                }catch (e:Exception){
                    Log.d("test_callback", e.message!!)
                }
            }
            override fun onError(data: String) {
                Log.d("test_callback", data)
            }
        })
        return loginData
    }

    fun getVersion(): SingleLiveData<VersionDataType> {
        repository.getVersion(object : OnCallBack {
            override fun onFinish(data: String) {
                try {
                    val jsonData = Gson().fromJson<VersionDataType>(data, object : TypeToken<VersionDataType>() {}.type)
                    versionData.postValue(jsonData)
                }catch (e:Exception){
                }
            }
            override fun onError(data: String) {
            }
        })
        return versionData
    }
}