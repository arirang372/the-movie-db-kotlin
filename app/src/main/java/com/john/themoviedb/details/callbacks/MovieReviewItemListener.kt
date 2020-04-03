package com.john.themoviedb.details.callbacks

import com.john.themoviedb.details.model.Review


interface MovieReviewItemListener {
    fun onReviewClicked(review: Review)
}