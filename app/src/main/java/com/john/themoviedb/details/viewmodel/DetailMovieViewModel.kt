package com.john.themoviedb.details.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import com.john.themoviedb.data.MovieRepository
import com.john.themoviedb.data.source.DataSource


class DetailMovieViewModel(application: Application, repository: MovieRepository) :
    AndroidViewModel(application), DataSource.LoadReviewsTrailersCallback {

    private val mRepository: MovieRepository = repository
    val trailersReviews: ObservableList<Comparable<*>> = ObservableArrayList()
    val dataLoading: ObservableBoolean = ObservableBoolean(false)

    private fun setDataLoading(isLoading: Boolean) {
        dataLoading.set(isLoading)
    }

    override fun onReviewsTrailersLoaded() {
        setDataLoading(false)
    }

    override fun onReviewsTrailersNotAvailable() {
        setDataLoading(false)
    }

    fun loadMovieTrailersAndReviews(movieId: Long) {
        setDataLoading(true)
        mRepository.loadReviewsAndTrailers(movieId, this, trailersReviews)
    }
}