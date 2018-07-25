package com.manuelperera.topmovies20.domain.model.base


interface ResponseObject<out DomainObject : Any> {

    fun toAppDomain(): DomainObject

}