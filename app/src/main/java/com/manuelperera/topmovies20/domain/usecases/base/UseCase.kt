package com.manuelperera.topmovies20.domain.usecases.base

interface UseCase<out T> {

    fun bind(): T

}