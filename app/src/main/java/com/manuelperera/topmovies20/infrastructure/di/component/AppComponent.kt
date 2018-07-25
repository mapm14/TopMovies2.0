package com.manuelperera.topmovies20.infrastructure.di.component

import com.manuelperera.topmovies20.infrastructure.TopMoviesApp
import com.manuelperera.topmovies20.infrastructure.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<TopMoviesApp>