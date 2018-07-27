package com.manuelperera.topmovies20.domain.model

import java.io.Serializable


data class MovieDetail(
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
) : Serializable