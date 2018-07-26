package com.manuelperera.topmovies20.presentation.main

import arrow.core.Either
import com.manuelperera.topmovies20.domain.usecases.movie.GetMovieListUseCase
import com.manuelperera.topmovies20.presentation.base.Presenter
import javax.inject.Inject


class MainPresenter @Inject constructor(private val getMovieListUseCase: GetMovieListUseCase) : Presenter<MainView>() {

    override fun init() {
        getMovieList(1)
    }

    private fun getMovieList(page: Int) {
        addSubscription(getMovieListUseCase.bind(GetMovieListUseCase.Dto(page)).subscribe { eMovieList ->
            when (eMovieList) {
                is Either.Right -> view?.showMovieInfo(eMovieList.b.list[0])
                is Either.Left -> view?.showToast(eMovieList.a.info.message)
            }
        })
    }

}