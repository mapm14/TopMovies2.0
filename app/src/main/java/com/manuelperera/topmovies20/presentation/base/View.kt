package com.manuelperera.topmovies20.presentation.base


interface View {

    fun showToast(message: String, resId: Int = -1)

}