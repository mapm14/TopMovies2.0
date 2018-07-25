package com.manuelperera.topmovies20.domain.repository

import arrow.core.Either
import com.manuelperera.topmovies20.domain.model.base.Status
import com.manuelperera.topmovies20.domain.model.Config
import io.reactivex.Observable


interface ConfigRepository {

    fun getConfig(): Observable<Either<Status, Config>>

}