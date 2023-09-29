package com.john.themoviedb.details.view.viewholders

import com.john.themoviedb.databinding.ReviewListContentBinding
import com.john.themoviedb.details.callbacks.MovieReviewItemListener
import com.john.themoviedb.details.model.Review
import com.john.themoviedb.details.viewmodel.DetailMovieViewModel


class ReviewViewHolder(
    private val binding: ReviewListContentBinding,
    private val viewModel: DetailMovieViewModel,
    private val trailersReviews: MutableList<Comparable<*>>,
    private val callback: MovieReviewItemListener
) : BaseViewHolder<Review>(binding.root) {
    override fun bind(item: Review) {
        binding.model = item
        binding.callback = object : MovieReviewItemListener {
            override fun onReviewClicked(review: Review) {
                if (adapterPosition < 0) return
                val review = trailersReviews[adapterPosition] as Review
                callback.onReviewClicked(review)
            }
        }
        binding.viewModel = viewModel
    }
}