package com.manuelperera.topmovies20.domain.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


fun ImageView.loadImage(path: String, onExceptionDelegate: () -> Unit = {}, onResourceReadyDelegate: () -> Unit = {}) {
    Glide.with(this.context)
            .load(path)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    onExceptionDelegate()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    onResourceReadyDelegate()
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