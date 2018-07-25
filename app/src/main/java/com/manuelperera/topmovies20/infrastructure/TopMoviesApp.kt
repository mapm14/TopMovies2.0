package com.manuelperera.topmovies20.infrastructure

import com.manuelperera.topmovies20.infrastructure.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class TopMoviesApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.create()

}