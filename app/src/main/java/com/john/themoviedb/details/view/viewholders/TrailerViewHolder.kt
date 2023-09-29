package com.john.themoviedb.details.view.viewholders

import com.john.themoviedb.databinding.TrailerListContentBinding
import com.john.themoviedb.details.callbacks.MovieTrailerItemListener
import com.john.themoviedb.details.model.Trailer
import com.john.themoviedb.details.viewmodel.DetailMovieViewModel


class TrailerViewHolder(
    private val binding: TrailerListContentBinding,
    private val viewModel: DetailMovieViewModel,
    private val trailersReviews: MutableList<Comparable<*>>,
    private val callback: MovieTrailerItemListener
) :
    BaseViewHolder<Trailer>(binding.root) {
    override fun bind(item: Trailer) {
        binding.model = item
        binding.callback = object : MovieTrailerItemListener {
            override fun onTrailerItemClicked(trailer: Trailer) {
                if (adapterPosition < 0) return
                val trailer: Trailer = trailersReviews[adapterPosition] as Trailer
                callback.onTrailerItemClicked(trailer)
            }
        }
        binding.viewModel = viewModel
    }
}