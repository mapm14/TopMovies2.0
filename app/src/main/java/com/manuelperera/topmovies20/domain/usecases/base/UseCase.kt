package com.manuelperera.topmovies20.domain.usecases.base

import arrow.core.Either
import com.manuelperera.topmovies20.domain.model.base.Status
import io.reactivex.Observable
import io.reactivex.disposables.Disposable


abstract class UseCase<Type, in Params> {

    abstract fun bind(params: Params): Observable<Either<Status, Type>>

    operator fun invoke(params: Params, onResult: (Either<Status, Type>) -> Unit = {}): Disposable =
            bind(params).subscribe { onResult(it) }

}