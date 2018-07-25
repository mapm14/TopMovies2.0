package com.manuelperera.topmovies20.data.repository.datasources.api.config.model

import com.google.gson.annotations.SerializedName
import com.manuelperera.topmovies20.domain.model.Config
import com.manuelperera.topmovies20.domain.model.base.ResponseObject


class ConfigResponse(
        @SerializedName("images") private val images: ImagesResponse,
        @SerializedName("change_keys") private val changeKeys: List<String>
) : ResponseObject<Config> {

    inner class ImagesResponse(
            @SerializedName("base_url") val baseUrl: String,
            @SerializedName("secure_base_url") val secureBaseUrl: String,
            @SerializedName("backdrop_sizes") val backdropSizes: List<String>,
            @SerializedName("logo_sizes") val logoSizes: List<String>,
            @SerializedName("poster_sizes") val posterSizes: List<String>,
            @SerializedName("profile_sizes") val profileSizes: List<String>,
            @SerializedName("still_sizes") val stillSizes: List<String>
    )

    override fun toAppDomain(): Config =
            Config(images.secureBaseUrl, images.posterSizes)

}