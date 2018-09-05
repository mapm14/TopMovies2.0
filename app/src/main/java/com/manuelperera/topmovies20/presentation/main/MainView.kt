package com.manuelperera.topmovies20.presentation.main

import com.manuelperera.topmovies20.presentation.base.View

interface MainView : View {

    fun onLoadPageSuccess(list: List<MovieDetailUI>)

    fun onLoadPageError(isFirstPage: Boolean)

}