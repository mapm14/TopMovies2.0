package com.manuelperera.topmovies20.presentation.detail

import com.manuelperera.topmovies20.domain.model.MovieDetail
import com.manuelperera.topmovies20.presentation.base.View


interface DetailView : View {

    fun showMovieInfo(movieDetail: MovieDetail)

}