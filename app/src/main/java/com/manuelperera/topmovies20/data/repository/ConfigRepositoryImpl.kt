package com.manuelperera.topmovies20.data.repository

import arrow.core.Either
import com.manuelperera.topmovies20.data.repository.base.BaseRepository
import com.manuelperera.topmovies20.domain.model.base.Status
import com.manuelperera.topmovies20.data.repository.datasources.api.config.ConfigApi
import com.manuelperera.topmovies20.domain.model.Config
import com.manuelperera.topmovies20.domain.repository.ConfigRepository
import io.reactivex.Observable
import javax.inject.Inject


class ConfigRepositoryImpl @Inject constructor(private val configApi: ConfigApi) : BaseRepository(), ConfigRepository {

    override fun getConfig(): Observable<Either<Status, Config>> =
            configApi.getConfig().modifyObservable()

}