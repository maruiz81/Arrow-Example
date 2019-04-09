package com.maruiz.arrowexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maruiz.arrowexample.data.failure.Failure

abstract class BaseViewModel : ViewModel() {
    private val failure: MutableLiveData<Failure> = MutableLiveData()

    fun observeFailure(): LiveData<Failure> = failure

    protected fun handleFailure(failure: Throwable) {
        this.failure.value = Failure.ServerError
    }

    override fun onCleared() {
        super.onCleared()
        cancelRequest()
    }

    abstract fun cancelRequest()
}