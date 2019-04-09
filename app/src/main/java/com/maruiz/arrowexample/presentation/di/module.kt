package com.maruiz.arrowexample.presentation.di

import arrow.integrations.retrofit.adapter.CallKindAdapterFactory
import com.maruiz.arrowexample.data.services.BookApi
import com.maruiz.arrowexample.domain.GetBooks
import com.maruiz.arrowexample.presentation.viewmodel.BooksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(getProperty<String>("base_url"))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CallKindAdapterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(BookApi::class.java) }
    single { GetBooks(get()) }
    viewModel { BooksViewModel(get()) }
}