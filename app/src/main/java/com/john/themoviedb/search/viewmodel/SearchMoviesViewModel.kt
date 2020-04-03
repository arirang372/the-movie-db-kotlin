package com.john.themoviedb.search.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.john.themoviedb.R
import com.john.themoviedb.data.MovieConstants
import com.john.themoviedb.data.MovieRepository
import com.john.themoviedb.data.source.DataSource
import com.john.themoviedb.search.model.Movie

class SearchMoviesViewModel(application: Application, repository: MovieRepository) :
    AndroidViewModel(application), DataSource.LoadMoviesCallback {
    private val mRepository: MovieRepository = repository
    val dataLoading: ObservableBoolean = ObservableBoolean(false)
    val movies: ObservableList<Movie> = ObservableArrayList()
    val sortByField = ObservableField<String>()
    private val openTaskEvent = MutableLiveData<Movie>()

    init {
        sortByField.set(MovieConstants.SortBy.MOST_POPULAR)
    }

    private fun setDataLoading(isLoading: Boolean) {
        dataLoading.set(isLoading)
    }

    override fun onMoviesLoaded() {
        setDataLoading(false)
    }

    override fun onMovieNotAvailable() {
        setDataLoading(false)
    }

    fun loadAllMovies(resourceId: Int) {
        when (resourceId) {
            R.id.sort_by_popular -> {
                sortByField?.set(MovieConstants.SortBy.MOST_POPULAR)

            }
            R.id.sort_by_top_rated -> {
                sortByField?.set(MovieConstants.SortBy.TOP_RATED)
            }
        }
        if (resourceId != 0) {
            setDataLoading(true)
            mRepository.loadAllMovies(sortByField.get()!!, this, movies)
        }
    }

    fun setMovieDetail(movie: Movie) {
        openTaskEvent.value = movie
    }

    fun getOpenTaskEvent(): MutableLiveData<Movie> {
        return openTaskEvent
    }
}

