package com.manuelperera.topmovies20.domain.extensions

import arrow.core.Either
import arrow.core.getOrElse

fun <A : Any, B : Any> Either<A, B>.getNotNull(delegate: (b: B) -> Unit) {
    getOrElse { null }?.let { delegate(it) }
}