package com.sray.pigeonmap.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jwiseinc.onedayticket.viewmodel.LoginViewModel
import com.jwiseinc.onedayticket.model.repository.LoginRepository

class LoginViewModelFactory constructor(private val repository: LoginRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}