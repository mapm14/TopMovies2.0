package com.manuelperera.topmovies20.data.repository

import arrow.core.Either
import com.manuelperera.topmovies20.data.repository.base.BaseRepository
import com.manuelperera.topmovies20.data.repository.client.MovieApiClient
import com.manuelperera.topmovies20.domain.model.MovieDetail
import com.manuelperera.topmovies20.domain.model.MovieList
import com.manuelperera.topmovies20.domain.model.base.Status
import com.manuelperera.topmovies20.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(private val movieApi: MovieApiClient) : BaseRepository(), MovieRepository {

    override fun getTopRatedMovies(page: Int): Observable<Either<Status, MovieList>> =
            movieApi.client.getTopRatedMovies(page).modifyObservable()

    override fun getSimilarMovies(movieId: Int, page: Int): Observable<Either<Status, MovieList>> =
            movieApi.client.getSimilarMovies(movieId, page).modifyObservable()

    override fun getMovieDetail(movieId: Int): Observable<Either<Status, MovieDetail>> =
            movieApi.client.getMovieDetail(movieId).modifyObservable()

}