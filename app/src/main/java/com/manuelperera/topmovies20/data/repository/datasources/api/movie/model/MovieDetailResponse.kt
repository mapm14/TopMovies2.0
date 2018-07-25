package com.manuelperera.topmovies20.data.repository.datasources.api.movie.model

import com.google.gson.annotations.SerializedName
import com.manuelperera.topmovies20.domain.model.MovieDetail
import com.manuelperera.topmovies20.domain.model.base.ResponseObject


class MovieDetailResponse(
        @SerializedName("vote_count") private val voteCount: Int?,
        @SerializedName("id") private val id: Int?,
        @SerializedName("video") private val video: Boolean?,
        @SerializedName("vote_average") private val voteAverage: Double?,
        @SerializedName("title") private val title: String?,
        @SerializedName("popularity") private val popularity: Double?,
        @SerializedName("poster_path") private val posterPath: String?,
        @SerializedName("original_language") private val originalLanguage: String?,
        @SerializedName("original_title") private val originalTitle: String?,
        @SerializedName("genre_ids") private val genreIds: List<Int>?,
        @SerializedName("backdrop_path") private val backdropPath: String?,
        @SerializedName("adult") private val adult: Boolean?,
        @SerializedName("overview") private val overview: String?,
        @SerializedName("release_date") private val releaseDate: String?
) : ResponseObject<MovieDetail> {

    override fun toAppDomain(): MovieDetail =
            MovieDetail(
                    voteCount ?: 0,
                    id ?: 0,
                    video ?: false,
                    voteAverage ?: 0.0,
                    title ?: "",
                    popularity ?: 0.0,
                    posterPath ?: "",
                    originalLanguage ?: "",
                    originalTitle ?: "",
                    genreIds ?: listOf(),
                    backdropPath ?: "",
                    adult ?: false,
                    overview ?: "",
                    releaseDate ?: ""
            )

}