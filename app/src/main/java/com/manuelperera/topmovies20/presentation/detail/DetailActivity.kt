package com.manuelperera.topmovies20.presentation.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
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
        fun goToDetailActivity(context: Context, movieDetail: MovieDetail, sharedImageView: ImageView) {
            val intent = Intent(context, DetailActivity::class.java).apply {
                setParamByClass(movieDetail)
            }
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    context as Activity,
                    sharedImageView,
                    ViewCompat.getTransitionName(sharedImageView) ?: ""
            )
            context.startActivity(intent, options.toBundle())
        }
    }

    @Inject
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportPostponeEnterTransition()
        val movieDetail: MovieDetail = getParamByClass()
        ViewCompat.setTransitionName(posterImgView, movieDetail.id.toString())
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
        posterImgView.loadImage(movieDetail.posterPath) { supportStartPostponedEnterTransition() }
        titleTxtView.text = movieDetail.title
    }

}