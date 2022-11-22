package com.jwiseinc.onedayticket.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jwiseinc.onedayticket.OnCallBack
import com.jwiseinc.onedayticket.SingleLiveData
import com.jwiseinc.onedayticket.model.data.MotifyPasswordDataType
import com.jwiseinc.onedayticket.model.repository.MotifyPasswordRepository
import com.jwiseinc.onedayticket.view.activtiy.BaseActivity

class WriteOffRecordViewModel (private val repository: MotifyPasswordRepository) : ViewModel() {

    val motifyPasswordDataType = SingleLiveData<MotifyPasswordDataType>()

    fun resetPassword(authcode:String,newPassword:String,randNumber:String): SingleLiveData<MotifyPasswordDataType> {
        repository.resetPassword(authcode,newPassword,randNumber,object : OnCallBack {
            override fun onFinish(data: String) {
                try {
                    val jsonData = Gson().fromJson<MotifyPasswordDataType>(data, object : TypeToken<MotifyPasswordDataType>() {}.type)
                    motifyPasswordDataType.postValue(jsonData)
                }catch (e:Exception){
                    BaseActivity.loadingView.hide()
                }
            }
            override fun onError(data: String) {
                BaseActivity.loadingView.hide()
            }
        })
        return motifyPasswordDataType
    }
}