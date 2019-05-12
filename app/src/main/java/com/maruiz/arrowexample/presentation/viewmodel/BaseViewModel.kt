package com.maruiz.arrowexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maruiz.arrowexample.data.error.Failure

abstract class BaseViewModel : ViewModel() {
    private val failure: MutableLiveData<String> = MutableLiveData()

    fun observeFailure(): LiveData<String> = failure

    protected fun handleFailure(failure: Failure) {
        this.failure.value =  ""
    }

    override fun onCleared() {
        super.onCleared()
        cancelRequest()
    }

    abstract fun cancelRequest()
}