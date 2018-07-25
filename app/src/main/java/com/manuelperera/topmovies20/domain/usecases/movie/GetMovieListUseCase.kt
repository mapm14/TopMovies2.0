package com.manuelperera.topmovies20.domain.usecases.movie

import arrow.core.Either
import com.manuelperera.topmovies20.domain.extensions.getNotNull
import com.manuelperera.topmovies20.domain.extensions.subObs
import com.manuelperera.topmovies20.domain.model.Config
import com.manuelperera.topmovies20.domain.model.MovieList
import com.manuelperera.topmovies20.domain.model.base.Status
import com.manuelperera.topmovies20.domain.repository.MovieRepository
import com.manuelperera.topmovies20.domain.usecases.base.UseCaseDto
import com.manuelperera.topmovies20.domain.usecases.base.UseCaseWithDto
import com.manuelperera.topmovies20.domain.usecases.config.GetConfigUseCase
import io.reactivex.Observable
import javax.inject.Inject


class GetMovieListUseCase @Inject constructor(
        private val getConfigUseCase: GetConfigUseCase,
        private val movieRepository: MovieRepository
) : UseCaseWithDto<Observable<Either<Status, MovieList>>, GetMovieListUseCase.Dto> {

    override fun bind(dto: Dto): Observable<Either<Status, MovieList>> =
            getConfigUseCase.bind().flatMap { eConfig ->
                movieRepository.getTopRatedMovies(dto.page).doOnNext { eMovieList ->
                    addBaseUrlToImages(eConfig, eMovieList)
                }.subObs()
            }.subObs()

    private fun addBaseUrlToImages(eConfig: Either<Status, Config>, eMovieList: Either<Status, MovieList>) {
        eConfig.getNotNull { config ->
            eMovieList.getNotNull { movieList ->
                movieList.list.forEach { movieDetail ->
                    movieDetail.posterPath = config.getChromePosterSizeUrl() + movieDetail.posterPath
                }
            }
        }
    }

    class Dto(val page: Int) : UseCaseDto()

}