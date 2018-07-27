package com.manuelperera.topmovies20.domain.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.manuelperera.topmovies20.R
import com.mikhaellopez.circularimageview.CircularImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


fun ImageView.loadImage(path: String, onErrorDelegate: () -> Unit = {}, onSuccessDelegate: () -> Unit = {}) {
    Picasso
            .get()
            .load(path)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(this, object : Callback {
                override fun onSuccess() {
                    onSuccessDelegate()
                }

                override fun onError(e: Exception?) {
                    onErrorDelegate()
                }
            })
}

fun CircularImageView.loadImage(path: String, onErrorDelegate: () -> Unit = {}, onSuccessDelegate: () -> Unit = {}) {
    Picasso
            .get()
            .load(path)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(this, object : Callback {
                override fun onSuccess() {
                    onSuccessDelegate()
                }

                override fun onError(e: Exception?) {
                    onErrorDelegate()
                }
            })
}

fun View.hide() {
    visibility = GONE
}

fun View.show() {
    visibility = VISIBLE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)