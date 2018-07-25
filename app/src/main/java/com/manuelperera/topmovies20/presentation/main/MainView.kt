package com.manuelperera.topmovies20.presentation.main

import com.manuelperera.topmovies20.domain.model.MovieDetail
import com.manuelperera.topmovies20.presentation.base.View


interface MainView : View {

    fun showMovieInfo(movieDetail: MovieDetail)

}