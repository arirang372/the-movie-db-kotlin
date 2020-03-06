package com.john.themoviedb.data

import androidx.databinding.ObservableList
import com.john.themoviedb.data.source.DataSource
import com.john.themoviedb.data.source.remote.RemoteDataLoader
import com.john.themoviedb.search.model.Movie


class MovieRepository {
    private var dataLoader = RemoteDataLoader()

    fun loadAllMovies(
        sortBy: String, callback: DataSource.LoadMoviesCallback,
        observableList: ObservableList<Movie>
    ) {
        dataLoader.loadAllMovies(sortBy, callback, observableList)
    }
}