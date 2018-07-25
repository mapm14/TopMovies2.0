package com.manuelperera.topmovies20.infrastructure.di.api

import com.manuelperera.topmovies20.BuildConfig.BASE_URL
import com.manuelperera.topmovies20.BuildConfig.DEBUG
import com.manuelperera.topmovies20.BuildConfig.MOVIE_API_KEY
import com.manuelperera.topmovies20.data.repository.datasources.api.config.ConfigApi
import com.manuelperera.topmovies20.data.repository.datasources.api.movie.MovieApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient =
            OkHttpClient().newBuilder()
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

    @Provides
    fun movieApiClient(client: OkHttpClient): MovieApi =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(MovieApi::class.java)

    @Provides
    fun configApiClient(client: OkHttpClient): ConfigApi =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(ConfigApi::class.java)

}