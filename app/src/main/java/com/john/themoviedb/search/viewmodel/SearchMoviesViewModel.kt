package com.john.themoviedb.search.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import com.john.networklib_livedata.ConnectivityStatus
import com.john.themoviedb.R
import com.john.themoviedb.SingleLiveEvent
import com.john.themoviedb.common.viewModel.BaseViewModel
import com.john.themoviedb.data.MovieConstants
import com.john.themoviedb.data.MovieRepository
import com.john.themoviedb.data.source.DataSource
import com.john.themoviedb.search.callbacks.SearchMovieListener
import com.john.themoviedb.search.model.Movie

class SearchMoviesViewModel(application: Application, repository: MovieRepository) :
    BaseViewModel(application) {
    private val mRepository: MovieRepository = repository

    val movieList: ObservableList<Movie> = ObservableArrayList()
    val sortByField = ObservableField<String>()
    val isNetworkAvailable: ObservableBoolean = ObservableBoolean(false)
    lateinit var mSearchMovieListener : SearchMovieListener
    private val openTaskEvent = SingleLiveEvent<Movie>()

    init {
        sortByField.set(MovieConstants.SortBy.MOST_POPULAR)
    }

    fun setSearchMovieListener(searchMovieListener: SearchMovieListener){
        mSearchMovieListener = searchMovieListener
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
        if (resourceId != 0)
            loadMovies()
    }

    private fun loadMovies() {
        setDataLoading(true)
        mRepository.loadAllMovies(sortByField.get()!!, object : DataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: MutableList<Movie>) {
                setDataLoading(false)
                movieList.clear()
                movieList.addAll(movies)
                mSearchMovieListener.updateActionBar(sortByField.get()!!)
            }

            override fun onMovieNotAvailable() {
                setDataLoading(false)
                movieList.clear()
                mSearchMovieListener.updateActionBar(sortByField.get()!!)
            }
        })
    }

    fun updateFavoriteMovies() {
        if (sortByField.get()!!.contentEquals(MovieConstants.SortBy.FAVORITES)) {
            loadMovies()
        }
    }

    fun setIsNetworkAvailable(connectivityStatus: ConnectivityStatus) {
        if ((connectivityStatus == ConnectivityStatus.OFFLINE
                    || connectivityStatus == ConnectivityStatus.WIFI_CONNECTED_HAS_NO_INTERNET
                    || connectivityStatus == ConnectivityStatus.UNKNOWN)
        ) {
            if (!sortByField.get()!!.contentEquals(MovieConstants.SortBy.FAVORITES))
                isNetworkAvailable.set(false)
        } else {
            isNetworkAvailable.set(true)
            loadAllMovies(R.id.sort_by_popular)
        }
    }

    fun setMovieDetail(movie: Movie) {
        openTaskEvent.setValue(movie)
    }

    fun getOpenTaskEvent(): SingleLiveEvent<Movie> {
        return openTaskEvent
    }
}

