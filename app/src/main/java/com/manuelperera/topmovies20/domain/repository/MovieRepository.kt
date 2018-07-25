package com.manuelperera.topmovies20.domain.repository

import arrow.core.Either
import com.manuelperera.topmovies20.domain.model.base.Status
import com.manuelperera.topmovies20.domain.model.MovieDetail
import com.manuelperera.topmovies20.domain.model.MovieList
import io.reactivex.Observable


interface MovieRepository {

    fun getTopRatedMovies(page: Int): Observable<Either<Status, MovieList>>

    fun getSimilarMovies(movieId: Int, page: Int): Observable<Either<Status, MovieList>>

    fun getMovieDetail(movieId: Int): Observable<Either<Status, MovieDetail>>

}