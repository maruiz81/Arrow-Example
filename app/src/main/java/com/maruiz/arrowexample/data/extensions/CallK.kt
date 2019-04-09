package com.maruiz.arrowexample.data.extensions

import arrow.core.Either
import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.extensions.io.monad.map
import arrow.effects.fix
import arrow.integrations.retrofit.adapter.CallK

fun <R> CallK<R>.makeCall(default: R): IO<Either<Throwable, R>> =
    this.async(IO.async())
        .map { it.body() ?: default }
        .attempt()
        .fix()