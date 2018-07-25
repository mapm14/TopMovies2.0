package com.manuelperera.topmovies20.infrastructure.di.app

import com.manuelperera.topmovies20.infrastructure.di.activity.ActivityModule
import com.manuelperera.topmovies20.infrastructure.di.api.ApiModule
import com.manuelperera.topmovies20.infrastructure.di.fragment.FragmentModule
import com.manuelperera.topmovies20.infrastructure.di.repository.RepositoryModule
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule


@Module(includes = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    FragmentModule::class,
    RepositoryModule::class,
    ApiModule::class])
class AppModule