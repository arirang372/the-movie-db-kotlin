package com.john.themoviedb.details.view.viewholders

import com.john.themoviedb.databinding.TrailerListContentBinding
import com.john.themoviedb.details.callbacks.MovieTrailerItemListener
import com.john.themoviedb.details.model.Trailer
import com.john.themoviedb.details.viewmodel.DetailMovieViewModel


class TrailerViewHolder(
    binding: TrailerListContentBinding,
    viewModel: DetailMovieViewModel,
    trailersReviews: MutableList<Comparable<*>>,
    callback: MovieTrailerItemListener
) :
    BaseViewHolder<Trailer>(binding.root) {
    private var mTrailersReviews: MutableList<Comparable<*>> = trailersReviews
    private val mBinding = binding
    private val mCallback = callback
    private var mViewModel = viewModel
    override fun bind(item: Trailer) {
        mBinding.model = item
        mBinding.callback = object : MovieTrailerItemListener {
            override fun onTrailerItemClicked(trailer: Trailer) {
                if (adapterPosition < 0) return
                val trailer: Trailer = mTrailersReviews[adapterPosition] as Trailer
                mCallback.onTrailerItemClicked(trailer)
            }
        }
        mBinding.viewModel = mViewModel
    }
}