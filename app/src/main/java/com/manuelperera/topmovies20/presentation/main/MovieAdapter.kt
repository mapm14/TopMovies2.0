package com.manuelperera.topmovies20.presentation.main

import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.manuelperera.topmovies20.R
import com.manuelperera.topmovies20.domain.extensions.load
import com.manuelperera.topmovies20.domain.model.MovieDetail
import com.manuelperera.topmovies20.presentation.base.recyclers.PagingAdapter
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
        onItemClick: (MovieDetail, ImageView) -> Unit,
        onRetryClick: () -> Unit
) : PagingAdapter<MovieDetailUI>(onRetryClick) {

    override var itemLayout = R.layout.item_movie

    override var onBindItem: (View, MovieDetailUI) -> Unit = { itemView, movieDetail ->
        with(itemView) {
            itemView.posterImgView.load(movieDetail.posterPath)
            itemView.setOnClickListener { onItemClick(movieDetail.toMovieDetail(), itemView.posterImgView) }
            ViewCompat.setTransitionName(itemView.posterImgView as View, movieDetail.id.toString())
        }
    }

}