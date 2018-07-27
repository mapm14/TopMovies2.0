package com.manuelperera.topmovies20.presentation.main


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manuelperera.topmovies20.R
import com.manuelperera.topmovies20.domain.extensions.inflate
import com.manuelperera.topmovies20.domain.extensions.loadImage
import com.manuelperera.topmovies20.domain.model.MovieDetail
import kotlinx.android.synthetic.main.item_movie.view.*


class MovieAdapter(
        private val list: List<MovieDetail>,
        private val onClickItem: (MovieDetail) -> Unit
) : ListAdapter<MovieDetail, MovieAdapter.ViewHolder>(itemCallback) {

    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<MovieDetail>() {
            override fun areItemsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean =
                    oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.item_movie))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieDetail: MovieDetail) {
            itemView.posterImgView.loadImage(movieDetail.posterPath)
            itemView.setOnClickListener { onClickItem(movieDetail) }
        }
    }

}