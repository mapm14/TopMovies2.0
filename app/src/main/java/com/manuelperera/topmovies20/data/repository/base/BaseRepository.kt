package com.manuelperera.topmovies20.data.repository.base

import arrow.core.Either
import com.google.gson.Gson
import com.manuelperera.topmovies20.domain.model.base.ResponseObject
import com.manuelperera.topmovies20.domain.model.base.Status
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.adapter.rxjava2.Result
import java.util.concurrent.TimeUnit.SECONDS


open class BaseRepository {

    protected fun <R : ResponseObject<DomainObject>, DomainObject : Any> Observable<Result<R>>.modifyObservable(): Observable<Either<Status, DomainObject>> =
            flatMap { data ->
                Observable.create<Either<Status, DomainObject>> { observer ->
                    data.response()?.let { response ->
                        val body: R? = response.body()
                        val code = response.code()
                        val errorBody = response.errorBody()

                        val either = when {
                            code in 200..208 && body != null -> getDomainObject(body)
                            code in 200..208 && body == null -> getStatusSuccess(code)
                            code in 400..512 -> getStatusErrorWithErrorBody(errorBody)
                            else -> getStatusError()
                        }
                        observer.onNext(either)

                    } ?: observer.onNext(getStatusError())

                    observer.onComplete()
                }
            }.timeout(30, SECONDS, Observable.create<Either<Status, DomainObject>> { subscriber ->
                subscriber.onNext(getStatusTimeout())
                subscriber.onComplete()
            })

    @Suppress("UNCHECKED_CAST")
    private fun <T : ResponseObject<DomainObject>, DomainObject : Any> getDomainObject(body: T) =
            Either.right((body as ResponseObject<Any>).toAppDomain()) as Either<Status, DomainObject>

    private fun getStatusSuccess(code: Int) =
            Either.left(Status.Success(InfoResponse(code, "").toAppDomain()))

    private fun getStatusErrorWithErrorBody(errorBody: ResponseBody?) =
            Either.left(Status.Error(parseResponseInfo(errorBody).toAppDomain()))

    private fun getStatusError() = Either.left(Status.Error(InfoResponse().toAppDomain()))

    private fun getStatusTimeout() = Either.left(Status.Timeout(InfoResponse(0, "").toAppDomain()))

    private fun parseResponseInfo(responseBody: ResponseBody?): InfoResponse =
            responseBody?.let { Gson().fromJson(it.string(), InfoResponse::class.java) }
                    ?: InfoResponse()

}