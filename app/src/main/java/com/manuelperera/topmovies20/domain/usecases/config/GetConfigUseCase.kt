package com.manuelperera.topmovies20.domain.usecases.config

import arrow.core.None
import com.manuelperera.topmovies20.domain.extensions.subObs
import com.manuelperera.topmovies20.domain.model.Config
import com.manuelperera.topmovies20.domain.repository.ConfigRepository
import com.manuelperera.topmovies20.domain.usecases.base.UseCase
import javax.inject.Inject


class GetConfigUseCase @Inject constructor(private val configRepository: ConfigRepository) : UseCase<Config, None>() {

    override fun bind(params: None) = configRepository.getConfig().subObs()

}