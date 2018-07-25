package com.manuelperera.topmovies20.domain.model.base


sealed class Status(val info: Info) {
    class Success(i: Info) : Status(i)
    class Error(i: Info) : Status(i)
    class Timeout(i: Info) : Status(i)
}