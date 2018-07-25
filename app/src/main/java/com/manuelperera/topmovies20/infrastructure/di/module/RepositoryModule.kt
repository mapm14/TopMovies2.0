package com.manuelperera.topmovies20.infrastructure.di.module

import com.manuelperera.topmovies20.data.repository.ConfigRepositoryImpl
import com.manuelperera.topmovies20.data.repository.MovieRepositoryImpl
import com.manuelperera.topmovies20.domain.repository.ConfigRepository
import com.manuelperera.topmovies20.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule {

    @Binds
    abstract fun configRepository(configRepositoryImpl: ConfigRepositoryImpl): ConfigRepository

    @Binds
    abstract fun movieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

}