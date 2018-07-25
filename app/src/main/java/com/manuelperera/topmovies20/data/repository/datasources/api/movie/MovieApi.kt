package com.manuelperera.topmovies20.data.repository.datasources.api.movie

import com.manuelperera.topmovies20.data.repository.datasources.api.movie.model.MovieDetailResponse
import com.manuelperera.topmovies20.data.repository.datasources.api.movie.model.MovieListResponse
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int): Observable<Result<MovieListResponse>>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id") movieId: Int, @Query("page") page: Int): Observable<Result<MovieListResponse>>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int): Observable<Result<MovieDetailResponse>>

}