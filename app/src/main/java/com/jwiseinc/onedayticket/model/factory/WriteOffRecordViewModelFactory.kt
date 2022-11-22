package com.jwiseinc.onedayticket.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jwiseinc.onedayticket.model.repository.MotifyPasswordRepository
import com.jwiseinc.onedayticket.viewmodel.MotifyPasswordViewModel

class WriteOffRecordViewModelFactory constructor(private val repository: MotifyPasswordRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MotifyPasswordViewModel::class.java)) {
            MotifyPasswordViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}