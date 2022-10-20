package com.sray.pigeonmap.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jwiseinc.onedayticket.model.repository.ForgetPasswordRepository
import com.jwiseinc.onedayticket.viewmodel.ForgetPasswordViewModel


class ForgetPasswordViewModelFactory constructor(private val repository: ForgetPasswordRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ForgetPasswordViewModel::class.java)) {
            ForgetPasswordViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}