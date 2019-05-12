package com.maruiz.arrowexample.domain

import arrow.effects.IO
import arrow.effects.extensions.io.fx.fxCancellable
import arrow.effects.fix
import kotlinx.coroutines.Dispatchers

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract fun run(params: Params): IO<Type>

    private lateinit var disposable: () -> Unit

    operator fun invoke(params: Params): IO<Type> =
        fxCancellable {
            val value = !run(params)
            continueOn(Dispatchers.Main)
            value
        }.let {
            disposable = it.b
            it.a.fix()
        }


    fun cancelRequest() {
        disposable()
    }
}