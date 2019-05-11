package com.maruiz.arrowexample.domain

import arrow.effects.IO
import arrow.effects.extensions.io.fx.fx
import kotlinx.coroutines.Dispatchers

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract fun run(params: Params): IO<Type>

    operator fun invoke(params: Params): IO<Type> =
        fx {
            val value = !run(params)
            continueOn(Dispatchers.Main)
            value
        }
}