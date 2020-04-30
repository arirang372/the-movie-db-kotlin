package com.john.themoviedb.details.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import com.john.themoviedb.common.viewModel.BaseViewModel
import com.john.themoviedb.data.MovieRepository
import com.john.themoviedb.data.source.DataSource
import com.john.themoviedb.search.model.Movie


class DetailMovieViewModel(application: Application, repository: MovieRepository) :
    BaseViewModel(application) {

    private val mRepository: MovieRepository = repository
    val trailersReviews: ObservableList<Comparable<*>> = ObservableArrayList()
    val isFavorite: ObservableBoolean = ObservableBoolean(false)

    fun loadMovieTrailersAndReviews(movieId: Long) {
        setDataLoading(true)
        setMovieFavorite(movieId)
        mRepository.loadReviewsAndTrailers(
            movieId,
            object : DataSource.LoadReviewsTrailersCallback {
                override fun onReviewsTrailersLoaded(reviewsTrailers: MutableList<Comparable<*>>) {
                    setDataLoading(false)
                    trailersReviews.clear()
                    trailersReviews.addAll(reviewsTrailers)
                }

                override fun onReviewsTrailersNotAvailable() {
                    setDataLoading(false)
                }
            })
    }

    private fun setMovieFavorite(id: Long) {
        mRepository.getMovie(id, object : DataSource.GetMovieCallback {
            override fun onMovieLoaded(movie: Movie) {
                isFavorite.set(true)
            }

            override fun onDataNotAvailable() {
                isFavorite.set(false)
            }
        })
    }

    fun markMovieAsFavorite(movie: Movie) {
        mRepository.saveMovie(movie)
        setMovieFavorite(movie.id)
    }

    fun removeMovieFromFavorite(movie: Movie) {
        mRepository.deleteMovie(movie.id)
        setMovieFavorite(movie.id)
    }
}