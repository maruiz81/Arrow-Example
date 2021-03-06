package com.maruiz.arrowexample.domain

import arrow.core.None
import arrow.fx.IO
import com.maruiz.arrowexample.data.extensions.makeCall
import com.maruiz.arrowexample.data.model.BookModel
import com.maruiz.arrowexample.data.services.BookApi

class GetBooks(private val bookApi: BookApi) : UseCase<List<BookModel>, None>() {
    override fun run(params: None): IO<List<BookModel>> = bookApi.getBooks().makeCall(emptyList())
}
