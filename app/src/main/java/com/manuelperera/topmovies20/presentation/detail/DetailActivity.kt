package com.manuelperera.topmovies20.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.manuelperera.topmovies20.R
import com.manuelperera.topmovies20.domain.extensions.getParamByClass
import com.manuelperera.topmovies20.domain.extensions.hide
import com.manuelperera.topmovies20.domain.extensions.loadImage
import com.manuelperera.topmovies20.domain.extensions.setParamByClass
import com.manuelperera.topmovies20.domain.extensions.show
import com.manuelperera.topmovies20.domain.model.MovieDetail
import com.manuelperera.topmovies20.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject


class DetailActivity : BaseActivity(), DetailView {

    companion object {
        fun goToDetailActivity(context: Context, movieDetail: MovieDetail) {
            val intent = Intent(context, DetailActivity::class.java).apply { setParamByClass(movieDetail) }
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val movieDetail: MovieDetail = getParamByClass()
        presenter.init(this)
        showMovieInfo(movieDetail)
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