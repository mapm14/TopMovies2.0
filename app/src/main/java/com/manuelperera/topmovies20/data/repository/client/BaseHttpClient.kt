package com.manuelperera.topmovies20.data.repository.client

import com.manuelperera.topmovies20.BuildConfig.DEBUG
import com.manuelperera.topmovies20.BuildConfig.MOVIE_API_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BaseHttpClient @Inject constructor() {

    val client: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (DEBUG) BODY else NONE
            })
            .addInterceptor { chain ->
                var request = chain.request()
                val httpBuilder = request.url().newBuilder()
                        .addQueryParameter("api_key", MOVIE_API_KEY)
                        .addQueryParameter("language", "en-US")
                val url = httpBuilder.build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }
            .build()

}