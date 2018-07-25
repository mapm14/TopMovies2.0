package com.manuelperera.topmovies20.data.repository.datasources.api.config

import com.manuelperera.topmovies20.data.repository.datasources.api.config.model.ConfigResponse
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET


interface ConfigApi {

    @GET("configuration")
    fun getConfig(): Observable<Result<ConfigResponse>>

}