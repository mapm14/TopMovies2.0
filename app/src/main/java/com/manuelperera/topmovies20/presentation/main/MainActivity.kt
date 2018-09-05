package com.manuelperera.topmovies20.presentation.main

import android.os.Bundle
import android.widget.ImageView
import com.manuelperera.topmovies20.R
import com.manuelperera.topmovies20.domain.extensions.setUpGridLayoutManager
import com.manuelperera.topmovies20.domain.extensions.setUpSwipeRefresh
import com.manuelperera.topmovies20.domain.model.MovieDetail
import com.manuelperera.topmovies20.presentation.base.BaseActivity
import com.manuelperera.topmovies20.presentation.base.recyclers.ScrollListener
import com.manuelperera.topmovies20.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var scrollListener: ScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.init(this)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        movieAdapter = MovieAdapter(::goToMovieDetail) { requestPage() }
        recyclerView.adapter = movieAdapter
        recyclerView.setUpGridLayoutManager(movieAdapter.list)

        setUpScrollListener()
        setSwipeRefresh()
        requestPage()
    }

    private fun setUpScrollListener() {
        recyclerView.layoutManager?.let {
            scrollListener = ScrollListener(it, ::requestPage)
            recyclerView.addOnScrollListener(scrollListener)
        }
    }

    private fun setSwipeRefresh() {
        swipeRefresh.setUpSwipeRefresh {
            scrollListener.reset()
            movieAdapter.clear()
            presenter.getMoviePage(1)
        }
    }

    private fun requestPage(page: Int = scrollListener.page) {
        movieAdapter.addLoading(page == 1)
        presenter.getMoviePage(page)
    }

    override fun onLoadPageSuccess(list: List<MovieDetailUI>) {
        swipeRefresh.isRefreshing = false
        movieAdapter.addItemsToList(list)
    }

    override fun onLoadPageError(isFirstPage: Boolean) {
        swipeRefresh.isRefreshing = false
        movieAdapter.addError(isFirstPage)
    }

    private fun goToMovieDetail(movieDetail: MovieDetail, sharedImageView: ImageView) {
        DetailActivity.goToDetailActivity(this, movieDetail, sharedImageView)
    }

    override fun onDestroy() {
        presenter.clear()
        super.onDestroy()
    }

}