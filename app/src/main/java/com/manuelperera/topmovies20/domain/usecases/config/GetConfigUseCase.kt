package com.manuelperera.topmovies20.domain.usecases.config

import arrow.core.Either
import com.manuelperera.topmovies20.domain.extensions.subObs
import com.manuelperera.topmovies20.domain.model.Config
import com.manuelperera.topmovies20.domain.model.base.Status
import com.manuelperera.topmovies20.domain.repository.ConfigRepository
import com.manuelperera.topmovies20.domain.usecases.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject


class GetConfigUseCase @Inject constructor(private val configRepository: ConfigRepository) : UseCase<Observable<Either<Status, Config>>> {

    override fun bind(): Observable<Either<Status, Config>> =
            configRepository.getConfig().subObs()

}