package com.manuelperera.topmovies20.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class Presenter<V : View> {
    private lateinit var mCompositeDisposable: CompositeDisposable
    protected var view: V? = null

    fun init(view: V) {
        this.view = view
        mCompositeDisposable = CompositeDisposable()
        init()
    }

    fun clear() {
        view = null
        mCompositeDisposable.dispose()
    }

    protected fun addSubscription(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    protected abstract fun init()
}