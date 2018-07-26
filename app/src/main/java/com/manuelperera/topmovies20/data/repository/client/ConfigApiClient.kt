package com.manuelperera.topmovies20.data.repository.client

import com.manuelperera.topmovies20.BuildConfig.BASE_URL
import com.manuelperera.topmovies20.data.repository.datasources.api.config.ConfigApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ConfigApiClient @Inject constructor(baseHttpClient: BaseHttpClient) {

    val client: ConfigApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(baseHttpClient.client)
            .build()
            .create(ConfigApi::class.java)

}