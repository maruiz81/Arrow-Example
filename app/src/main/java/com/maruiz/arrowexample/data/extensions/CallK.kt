package com.maruiz.arrowexample.data.extensions

import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.extensions.io.monad.map
import arrow.effects.fix
import arrow.integrations.retrofit.adapter.CallK

fun <R> CallK<R>.makeCall(default: R): IO<R> =
    this.async(IO.async())
        .map { it.body() ?: default }
        .fix()