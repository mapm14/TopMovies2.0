package com.manuelperera.topmovies20.data.repository.datasources.api.movie.model

import com.google.gson.annotations.SerializedName
import com.manuelperera.topmovies20.domain.model.MovieList
import com.manuelperera.topmovies20.domain.model.base.ResponseObject


class MovieListResponse(
        @SerializedName("page") private val page: Int?,
        @SerializedName("total_results") private val totalResults: Int?,
        @SerializedName("total_pages") private val totalPages: Int?,
        @SerializedName("results") private val list: MutableList<MovieDetailResponse>?
) : ResponseObject<MovieList> {

    override fun toAppDomain(): MovieList =
            MovieList(
                    page ?: 0,
                    totalResults ?: 0,
                    totalPages ?: 0,
                    list?.map { it.toAppDomain() }?.toMutableList() ?: mutableListOf()
            )

}