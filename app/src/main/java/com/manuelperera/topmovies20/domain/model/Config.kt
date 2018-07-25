package com.manuelperera.topmovies20.domain.model


class Config(
        val secureBaseUrl: String,
        val posterSizes: List<String>
) {

    fun getChromePosterSizeUrl(): String =
            when {
                posterSizes.contains("w500") -> secureBaseUrl + "w500"
                posterSizes.isNotEmpty() -> secureBaseUrl + posterSizes[0]
                else -> ""
            }

}