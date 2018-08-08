package com.manuelperera.topmovies20.domain.model


class MovieList(
        val page: Int,
        val totalResults: Int,
        val totalPages: Int,
        val list: MutableList<MovieDetail>
)