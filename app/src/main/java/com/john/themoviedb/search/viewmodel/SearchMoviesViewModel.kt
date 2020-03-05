package com.john.themoviedb.search.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import com.john.themoviedb.data.MovieRepository
import com.john.themoviedb.data.source.DataSource
import com.john.themoviedb.search.model.Movie

class SearchMoviesViewModel(application: Application, repository: MovieRepository) :
    AndroidViewModel(application), DataSource.LoadMoviesCallback {
    private val mRepository: MovieRepository = repository
    val dataLoading: ObservableBoolean = ObservableBoolean(false)
    val movies: ObservableList<Movie> = ObservableArrayList<Movie>()
    private fun setDataLoading(isLoading: Boolean) {
        dataLoading.set(isLoading)
    }

    override fun onMoviesLoaded() {
        setDataLoading(false)
    }

    override fun onMovieNotAvailable() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun loadAllMovies(sortBy: String) {
        mRepository.loadAllMovies(sortBy, this, movies)
    }
}

