package com.maruiz.arrowexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.core.None
import com.maruiz.arrowexample.data.model.BookModel
import com.maruiz.arrowexample.domain.GetBooks

class BooksViewModel(private val getBooks: GetBooks) : BaseViewModel() {
    private val books = MutableLiveData<List<BookModel>>()
    fun observeBooks(): LiveData<List<BookModel>> = books

    fun requestBooks() = getBooks(None).unsafeRunAsync { it.fold(::handleFailure, ::handleSuccess) }

    private fun handleSuccess(books: Either<Throwable, List<BookModel>>) {
        books.fold(::handleFailure) { this.books.value = it }
    }

    override fun cancelRequest() {
        //TODO
    }
}