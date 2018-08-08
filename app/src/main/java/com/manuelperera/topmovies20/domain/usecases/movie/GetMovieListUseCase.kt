package com.manuelperera.topmovies20.domain.usecases.movie

import arrow.core.Either
import arrow.core.None
import com.manuelperera.topmovies20.domain.extensions.subObs
import com.manuelperera.topmovies20.domain.model.Config
import com.manuelperera.topmovies20.domain.model.MovieList
import com.manuelperera.topmovies20.domain.model.base.Status
import com.manuelperera.topmovies20.domain.repository.MovieRepository
import com.manuelperera.topmovies20.domain.usecases.base.UseCase
import com.manuelperera.topmovies20.domain.usecases.config.GetConfigUseCase
import io.reactivex.Observable
import javax.inject.Inject


class GetMovieListUseCase @Inject constructor(
        private val getConfigUseCase: GetConfigUseCase,
        private val movieRepository: MovieRepository
) : UseCase<MovieList, GetMovieListUseCase.Params>() {

    override fun bind(params: Params): Observable<Either<Status, MovieList>> =
            getConfigUseCase.bind(None).flatMap { eConfig ->
                movieRepository.getTopRatedMovies(params.page).doOnNext { eMovieList ->
                    addBaseUrlToImages(eConfig, eMovieList)
                }.subObs()
            }

    private fun addBaseUrlToImages(eConfig: Either<Status, Config>, eMovieList: Either<Status, MovieList>) {
        if (eConfig is Either.Right && eMovieList is Either.Right) {
            eMovieList.b.list.forEach {
                it.posterPath = eConfig.b.getChromePosterSizeUrl() + it.posterPath
            }
        }
    }

    class Params(val page: Int)

}