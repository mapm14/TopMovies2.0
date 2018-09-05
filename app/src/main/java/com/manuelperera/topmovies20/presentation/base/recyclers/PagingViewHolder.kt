package com.manuelperera.topmovies20.presentation.base.recyclers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_small_error.view.*

abstract class PagingViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)

}

class LoadingViewHolder<in T>(val view: View) : PagingViewHolder<T>(view) {

    override fun bind(item: T) {}

}

class ErrorViewHolder<in T>(val view: View, val reloadAction: () -> Unit) : PagingViewHolder<T>(view) {

    override fun bind(item: T) {
        view.retryButton.setOnClickListener { reloadAction() }
    }

}

class ItemViewHolder<T : PagingObject>(itemView: View, var onBindItem: (View, T) -> Unit) : PagingViewHolder<T>(itemView) {

    override fun bind(item: T) {
        onBindItem(itemView, item)
    }

}