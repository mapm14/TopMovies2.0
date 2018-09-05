package com.manuelperera.topmovies20.domain.extensions

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.manuelperera.topmovies20.R
import com.manuelperera.topmovies20.infrastructure.di.module.GlideApp

fun ImageView.load(
        url: String,
        @DrawableRes drawableRes: Int = R.mipmap.ic_launcher_round,
        onSuccess: () -> Unit = {},
        onError: () -> Unit = {}
) {
    GlideApp
            .with(context)
            .load(url)
            .placeholder(drawableRes)
            .fitCenter()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    onError()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    onSuccess()
                    return false
                }
            })
            .into(this)
}

fun View.hide() {
    visibility = GONE
}

fun View.show() {
    visibility = VISIBLE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)