package com.manuelperera.topmovies20.presentation.main

import android.os.Bundle
import com.manuelperera.topmovies20.R
import com.manuelperera.topmovies20.domain.extensions.hide
import com.manuelperera.topmovies20.domain.extensions.loadImage
import com.manuelperera.topmovies20.domain.extensions.show
import com.manuelperera.topmovies20.domain.model.MovieDetail
import com.manuelperera.topmovies20.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.init(this)
    }

    override fun onDestroy() {
        presenter.clear()
        super.onDestroy()
    }

    override fun showMovieInfo(movieDetail: MovieDetail) {
        progressBar.hide()
        contentGroup.show()
        posterImgView.loadImage(movieDetail.posterPath)
        titleTxtView.text = movieDetail.title
    }

}