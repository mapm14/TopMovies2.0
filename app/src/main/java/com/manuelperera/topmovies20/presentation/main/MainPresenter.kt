package com.manuelperera.topmovies20.presentation.main

import arrow.core.Either.Left
import arrow.core.Either.Right
import com.manuelperera.topmovies20.domain.usecases.movie.GetMovieListUseCase
import com.manuelperera.topmovies20.domain.usecases.movie.GetMovieListUseCase.Params
import com.manuelperera.topmovies20.presentation.base.Presenter
import javax.inject.Inject


class MainPresenter @Inject constructor(private val getMovieListUseCase: GetMovieListUseCase) : Presenter<MainView>() {

    override fun init() {}

    fun getMoviePage(page: Int) {
        addSubscription(getMovieListUseCase(Params(page)) { eMovieList ->
            when (eMovieList) {
                is Right -> view?.showPage(eMovieList.b.list)
                is Left -> view?.showToast(eMovieList.a.info.message)
            }
        })
    }

}