package com.maruiz.arrowexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.None
import arrow.unsafe
import com.maruiz.arrowexample.data.model.BookModel
import com.maruiz.arrowexample.domain.GetBooks
import com.maruiz.arrowexample.presentation.presentationmodel.BookPresentationModel

class BooksViewModel(private val getBooks: GetBooks) : BaseViewModel() {
    private val books = MutableLiveData<List<BookPresentationModel>>()
    fun observeBooks(): LiveData<List<BookPresentationModel>> = books

    fun requestBooks() = unsafe {
        getBooks(None).unsafeRunSync().fold(::handleFailure, ::handleSuccess)
    }

    private fun handleSuccess(books: List<BookModel>) {
        this.books.value =
            books.map { BookPresentationModel(it.title, it.author, it.shortSynopsis, it.synopsis, it.image) }
    }

    override fun cancelRequest() {
        getBooks.cancelRequest()
    }
}