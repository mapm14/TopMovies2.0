package com.manuelperera.topmovies20.presentation.main

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


class EndlessRecyclerViewScrollListener(
        private val layoutManager: RecyclerView.LayoutManager,
        private val onPageIncrement: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private val visibleThreshold = 4

    private var page = 1

    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0
    // True if we are still waiting for the last set of data to load.
    private var isLoading = true

    private val minTotalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = layoutManager.itemCount

        when (layoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null)
                // get maximum element within the list
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            is LinearLayoutManager -> lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        }

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            page = 1
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                isLoading = true
            }
        }

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (isLoading && totalItemCount > previousTotalItemCount && totalItemCount - previousTotalItemCount > minTotalItemCount) {
            isLoading = false
            previousTotalItemCount = totalItemCount
        }

        if (!isLoading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            isLoading = true
            page++
            onPageIncrement(page)
        }
    }

    fun reset() {
        page = 1
        previousTotalItemCount = 0
        isLoading = true
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

}