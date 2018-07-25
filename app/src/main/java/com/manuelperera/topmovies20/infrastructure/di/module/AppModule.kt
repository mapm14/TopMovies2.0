package com.manuelperera.topmovies20.infrastructure.di.module

import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule


@Module(includes = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    FragmentModule::class,
    RepositoryModule::class,
    ApiModule::class])
class AppModule