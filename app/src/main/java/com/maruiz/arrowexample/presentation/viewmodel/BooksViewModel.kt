package com.maruiz.arrowexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.core.None
import com.maruiz.arrowexample.data.model.BookModel
import com.maruiz.arrowexample.domain.GetBooks
import com.maruiz.arrowexample.presentation.presentationmodel.BookPresentationModel

class BooksViewModel(private val getBooks: GetBooks) : BaseViewModel() {
    private val books = MutableLiveData<List<BookPresentationModel>>()
    fun observeBooks(): LiveData<List<BookPresentationModel>> = books

    fun requestBooks() = getBooks(None).unsafeRunAsync { it.fold(::handleFailure, ::handleSuccess) }

    private fun handleSuccess(books: Either<Throwable, List<BookModel>>) {
        books.fold(::handleFailure) { successBooks ->
            this.books.value =
                successBooks.map { BookPresentationModel(it.title, it.author, it.shortSynopsis, it.synopsis, it.image) }
        }
    }

    override fun cancelRequest() {
    }
}