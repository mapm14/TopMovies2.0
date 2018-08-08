package com.manuelperera.topmovies20.presentation.main

import android.os.Bundle
import android.widget.ImageView
import com.manuelperera.topmovies20.R
import com.manuelperera.topmovies20.domain.extensions.hide
import com.manuelperera.topmovies20.domain.extensions.show
import com.manuelperera.topmovies20.domain.model.MovieDetail
import com.manuelperera.topmovies20.presentation.base.BaseActivity
import com.manuelperera.topmovies20.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.init(this)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        movieAdapter = MovieAdapter(::goToMovieDetail)
        recyclerView.layoutManager?.let {
            scrollListener = EndlessRecyclerViewScrollListener(it) { presenter.getMoviePage(it) }
            recyclerView.addOnScrollListener(scrollListener)
        }
        recyclerView.adapter = movieAdapter
        presenter.getMoviePage(1)
    }

    override fun showPage(list: MutableList<MovieDetail>) {
        progressBar.hide()
        recyclerView.show()
        movieAdapter.addToList(list)
    }

    private fun goToMovieDetail(movieDetail: MovieDetail, sharedImageView: ImageView) {
        DetailActivity.goToDetailActivity(this, movieDetail, sharedImageView)
    }

    override fun onDestroy() {
        presenter.clear()
        super.onDestroy()
    }

}