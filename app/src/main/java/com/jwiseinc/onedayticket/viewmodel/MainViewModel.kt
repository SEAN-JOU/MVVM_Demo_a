package com.jwiseinc.onedayticket.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jwiseinc.onedayticket.OnCallBack
import com.jwiseinc.onedayticket.SingleLiveData
import com.jwiseinc.onedayticket.model.data.MainDataType
import com.jwiseinc.onedayticket.model.repository.MainRepository
import com.jwiseinc.onedayticket.view.activtiy.BaseActivity


class MainViewModel (private val repository: MainRepository) : ViewModel() {

    val mainData = SingleLiveData<MainDataType>()

    fun getData(memberID:String,session:String): SingleLiveData<MainDataType> {
        repository.getData(memberID,session,object : OnCallBack {
            override fun onFinish(data: String) {
                try {
                    val jsonData = Gson().fromJson<MainDataType>(data, object : TypeToken<MainDataType>() {}.type)
                    mainData.postValue(jsonData)
                }catch (e:Exception){
                }
            }
            override fun onError(data: String) {
            }
        })
        return mainData
    }

}