package com.jwiseinc.onedayticket.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jwiseinc.onedayticket.OnCallBack
import com.sray.pigeonmap.model.data.LoginDataType
import com.sray.pigeonmap.model.repository.LoginRepository


class LoginViewModel (private val repository: LoginRepository) : ViewModel() {

    val loginData = MutableLiveData<LoginDataType>()

    fun login(memberID:String,password:String): LiveData<LoginDataType> {
        repository.login(memberID,password,object : OnCallBack {
            override fun onFinish(data: String) {
                Log.d("aaaaaaaa",data)
                try {
                    val jsonData = Gson().fromJson<LoginDataType>(data, object : TypeToken<LoginDataType>() {}.type)
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