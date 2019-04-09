package com.maruiz.arrowexample.domain

import arrow.core.Either
import arrow.effects.IO
import arrow.effects.extensions.io.fx.fx
import kotlinx.coroutines.Dispatchers

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract fun run(params: Params): IO<Either<Throwable, Type>>

    operator fun invoke(params: Params): IO<Either<Throwable, Type>> =
        fx {
            val data = !run(params)
            continueOn(Dispatchers.Main)
            data
        }
//        run(params).flatMap { data ->
//            fx {
//                continueOn(Dispatchers.Main)
//                data
//            }
//        }
}