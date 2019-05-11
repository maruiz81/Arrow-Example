package com.maruiz.arrowexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private val failure: MutableLiveData<String> = MutableLiveData()

    fun observeFailure(): LiveData<String> = failure

    protected fun handleFailure(failure: Throwable) {
        this.failure.value = failure.message ?: ""
    }

    override fun onCleared() {
        super.onCleared()
        cancelRequest()
    }

    abstract fun cancelRequest()
}