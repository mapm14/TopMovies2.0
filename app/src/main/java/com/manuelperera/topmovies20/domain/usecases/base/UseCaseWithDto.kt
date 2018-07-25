package com.manuelperera.topmovies20.domain.usecases.base

interface UseCaseWithDto<out T, in P : UseCaseDto> {

    fun bind(dto: P): T

}