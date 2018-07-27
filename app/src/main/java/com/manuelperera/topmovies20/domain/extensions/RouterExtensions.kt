package com.manuelperera.topmovies20.domain.extensions


import android.content.Intent
import com.manuelperera.topmovies20.presentation.base.BaseActivity
import java.io.Serializable

//region BaseActivity extensions

inline fun <reified T : Serializable> Intent.setParamByClass(obj: T, tag: String = "") {
    putExtra(T::class.java.name + tag, obj)
}

inline fun <reified T : Serializable> BaseActivity.getParamByClass(data: Intent = intent, tag: String = ""): T =
        data.extras?.get(T::class.java.name + tag) as T

//endregion

//region BaseFragment extensions

//inline fun <reified T : Serializable> BaseFragment.setParamByClass(obj: T, tag: String = "") {
//    if (arguments == null) {
//        arguments = android.os.Bundle()
//    }
//    arguments?.putSerializable(T::class.java.name + tag, obj)
//}
//
//inline fun <reified T : Serializable> BaseFragment.getParamByClass(tag: String = ""): T =
//        arguments?.getSerializable(T::class.java.name + tag) as T

//endregion
