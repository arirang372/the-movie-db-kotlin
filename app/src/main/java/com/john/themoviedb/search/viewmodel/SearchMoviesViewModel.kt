package com.john.themoviedb.search.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import com.john.themoviedb.R
import com.john.themoviedb.SingleLiveEvent
import com.john.themoviedb.data.MovieConstants
import com.john.themoviedb.data.MovieRepository
import com.john.themoviedb.data.source.DataSource
import com.john.themoviedb.search.model.Movie

class SearchMoviesViewModel(application: Application, repository: MovieRepository) :
    AndroidViewModel(application) {
    private val mRepository: MovieRepository = repository
    val dataLoading: ObservableBoolean = ObservableBoolean(false)
    val movieList: ObservableList<Movie> = ObservableArrayList()
    val sortByField = ObservableField<String>()
    private val openTaskEvent = SingleLiveEvent<Movie>()

    init {
        sortByField.set(MovieConstants.SortBy.MOST_POPULAR)
    }

    private fun setDataLoading(isLoading: Boolean) {
        dataLoading.set(isLoading)
    }

    fun loadAllMovies(resourceId: Int) {
        when (resourceId) {
            R.id.sort_by_popular -> {
                sortByField?.set(MovieConstants.SortBy.MOST_POPULAR)

            }
            R.id.sort_by_top_rated -> {
                sortByField?.set(MovieConstants.SortBy.TOP_RATED)
            }
            R.id.sort_by_favorite -> {
                sortByField?.set(MovieConstants.SortBy.FAVORITES)
            }
        }
        if (resourceId != 0) {
            setDataLoading(true)
            mRepository.loadAllMovies(sortByField.get()!!, object : DataSource.LoadMoviesCallback {
                override fun onMoviesLoaded(movies: MutableList<Movie>) {
                    setDataLoading(false)
                    movieList.clear()
                    movieList.addAll(movies)
                }

                override fun onMovieNotAvailable() {
                    setDataLoading(false)
                }
            })
        }
    }

    fun setMovieDetail(movie: Movie) {
        openTaskEvent.setValue(movie)
    }

    fun getOpenTaskEvent(): SingleLiveEvent<Movie> {
        return openTaskEvent
    }
}

