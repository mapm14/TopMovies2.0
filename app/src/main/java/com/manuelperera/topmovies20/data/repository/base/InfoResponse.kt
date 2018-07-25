package com.manuelperera.topmovies20.data.repository.base

import com.google.gson.annotations.SerializedName
import com.manuelperera.topmovies20.domain.model.base.Info
import com.manuelperera.topmovies20.domain.model.base.ResponseObject


class InfoResponse(
        @SerializedName("status_code") private val code: Int = 400,
        @SerializedName("status_message") private val message: String = "Unknown Error"
) : ResponseObject<Info> {

    override fun toAppDomain(): Info = Info(code, message)

}