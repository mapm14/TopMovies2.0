package com.manuelperera.topmovies20.presentation.main

import com.manuelperera.topmovies20.domain.model.MovieDetail
import com.manuelperera.topmovies20.presentation.base.recyclers.PagingObject
import com.manuelperera.topmovies20.presentation.base.recyclers.PagingObject.ItemViewType.ITEM
import java.io.Serializable

data class MovieDetailUI(
        val voteCount: Int,
        val id: Int,
        val video: Boolean,
        val voteAverage: Double,
        val title: String,
        val popularity: Double,
        var posterPath: String,
        val originalLanguage: String,
        val originalTitle: String,
        val genreIds: List<Int>,
        val backdropPath: String,
        val adult: Boolean,
        val overview: String,
        val releaseDate: String
) : PagingObject, Serializable {

    override var itemViewType = ITEM

    fun toMovieDetail(): MovieDetail = MovieDetail(
            voteCount,
            id,
            video,
            voteAverage,
            title,
            popularity,
            posterPath,
            originalLanguage,
            originalTitle,
            genreIds,
            backdropPath,
            adult,
            overview,
            releaseDate
    )

}