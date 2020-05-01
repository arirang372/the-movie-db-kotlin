package com.john.themoviedb.details.view.viewholders

import com.john.themoviedb.databinding.ReviewListContentBinding
import com.john.themoviedb.details.callbacks.MovieReviewItemListener
import com.john.themoviedb.details.model.Review
import com.john.themoviedb.details.viewmodel.DetailMovieViewModel


class ReviewViewHolder(
    binding: ReviewListContentBinding,
    viewModel: DetailMovieViewModel,
    trailersReviews: MutableList<Comparable<*>>,
    callback: MovieReviewItemListener
) : BaseViewHolder<Review>(binding.root) {
    private var mTrailersReview = trailersReviews
    private var mBinding = binding
    private var mCallback = callback
    private var mViewModel = viewModel
    override fun bind(item: Review) {
        mBinding.model = item
        mBinding.callback = object : MovieReviewItemListener {
            override fun onReviewClicked(review: Review) {
                if (adapterPosition < 0) return
                val review = mTrailersReview[adapterPosition] as Review
                mCallback.onReviewClicked(review)
            }
        }
        mBinding.viewModel = mViewModel
    }
}