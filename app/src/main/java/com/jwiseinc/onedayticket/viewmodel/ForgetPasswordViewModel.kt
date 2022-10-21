package com.jwiseinc.onedayticket.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jwiseinc.onedayticket.OnCallBack
import com.jwiseinc.onedayticket.SingleLiveData
import com.jwiseinc.onedayticket.model.data.ForgetPasswordDataType
import com.jwiseinc.onedayticket.model.repository.ForgetPasswordRepository
import com.jwiseinc.onedayticket.view.activtiy.BaseActivity


class ForgetPasswordViewModel (private val repository: ForgetPasswordRepository) : ViewModel() {

    val forgetPasswordData = SingleLiveData<ForgetPasswordDataType>()

    fun forget(memberID:String,email:String): SingleLiveData<ForgetPasswordDataType> {
        repository.forget(memberID,email,object : OnCallBack {
            override fun onFinish(data: String) {
                try {
                    val jsonData = Gson().fromJson<ForgetPasswordDataType>(data, object : TypeToken<ForgetPasswordDataType>() {}.type)
                    forgetPasswordData.postValue(jsonData)
                }catch (e:Exception){
                    BaseActivity.loadingView.hide()
                }
            }
            override fun onError(data: String) {
                BaseActivity.loadingView.hide()
            }
        })
        return forgetPasswordData
    }
}