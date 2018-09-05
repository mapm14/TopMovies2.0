package com.manuelperera.topmovies20.presentation.base.recyclers

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuelperera.topmovies20.R
import com.manuelperera.topmovies20.domain.extensions.inflate
import com.manuelperera.topmovies20.presentation.base.recyclers.PagingObject.ItemViewType.FULL_ERROR
import com.manuelperera.topmovies20.presentation.base.recyclers.PagingObject.ItemViewType.FULL_LOADING
import com.manuelperera.topmovies20.presentation.base.recyclers.PagingObject.ItemViewType.ITEM
import com.manuelperera.topmovies20.presentation.base.recyclers.PagingObject.ItemViewType.SMALL_ERROR
import com.manuelperera.topmovies20.presentation.base.recyclers.PagingObject.ItemViewType.SMALL_LOADING

abstract class PagingAdapter<T : PagingObject>(
        private val onRetryClick: () -> Unit
) : RecyclerView.Adapter<PagingViewHolder<T>>() {

    protected abstract var onBindItem: (View, T) -> Unit
    protected abstract var itemLayout: Int

    protected var itemFullLoadingLayout = R.layout.item_full_loading
    protected var itemSmallLoadingLayout = R.layout.item_small_loading
    protected var itemFullErrorLayout = R.layout.item_full_error
    protected var itemSmallErrorLayout = R.layout.item_small_error

    var list = mutableListOf<T>()
        private set

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun addItemsToList(newList: List<T>) {
        addItem(ITEM, newList = newList)
    }

    fun addLoading(isFull: Boolean) {
        addItem(if (isFull) FULL_LOADING else SMALL_LOADING)
    }

    fun addError(isFull: Boolean) {
        addItem(if (isFull) FULL_ERROR else SMALL_ERROR)
    }

    @Synchronized
    @Suppress("UNCHECKED_CAST")
    private fun addItem(type: PagingObject.ItemViewType, newList: List<T>? = null) {
        val listHasErrorsOrLoading = list.any { it.itemViewType != ITEM }

        list.retainAll { it.itemViewType == ITEM }

        when (type) {
            FULL_LOADING, SMALL_LOADING, FULL_ERROR, SMALL_ERROR -> {
                if (listHasErrorsOrLoading) {
                    notifyItemRemoved(list.size)
                }

                val pagingObject = PagingObjectImpl().apply { itemViewType = type }
                list.add(pagingObject as T)

                notifyItemInserted(list.indexOf(pagingObject))
            }
            ITEM -> {
                notifyItemRemoved(list.size)

                newList?.let { list.addAll(it) }

                for (i in (list.size - 1)..(list.size + 19)) {
                    notifyItemInserted(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder<T> =
            when (viewType) {
                FULL_LOADING.ordinal -> LoadingViewHolder(parent.inflate(itemFullLoadingLayout))
                SMALL_LOADING.ordinal -> LoadingViewHolder(parent.inflate(itemSmallLoadingLayout))
                FULL_ERROR.ordinal -> ErrorViewHolder(parent.inflate(itemFullErrorLayout), onRetryClick)
                SMALL_ERROR.ordinal -> ErrorViewHolder(parent.inflate(itemSmallErrorLayout), onRetryClick)
                ITEM.ordinal -> ItemViewHolder(parent.inflate(itemLayout), onBindItem)
                else -> ErrorViewHolder(parent.inflate(itemSmallErrorLayout), onRetryClick)
            }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PagingViewHolder<T>, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int) = list[position].itemViewType.ordinal

}