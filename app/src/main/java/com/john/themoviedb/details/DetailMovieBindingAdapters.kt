package com.john.themoviedb.details

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.john.themoviedb.details.view.DetailMovieAdapter


object DetailMovieBindingAdapters {
    @JvmStatic
    @BindingAdapter("app:trailersReviews")
    fun setMovieTrailersAndReviews(
        recyclerView: RecyclerView,
        trailersAndReviews: MutableList<Comparable<*>>?
    ) {
        var movieDetailAdapter: DetailMovieAdapter = recyclerView.adapter as DetailMovieAdapter
        movieDetailAdapter.setMovieTrailersAndReviews(trailersAndReviews)
    }

}