package com.manuelperera.topmovies20.infrastructure.di.activity

import com.manuelperera.topmovies20.infrastructure.di.scope.ViewScope
import com.manuelperera.topmovies20.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ViewScope
    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity

}