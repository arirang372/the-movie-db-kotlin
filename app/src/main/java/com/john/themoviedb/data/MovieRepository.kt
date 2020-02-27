package com.john.themoviedb.data

import androidx.databinding.ObservableList
import com.john.themoviedb.data.source.DataSource
import com.john.themoviedb.data.source.remote.RemoteDataLoader
import com.john.themoviedb.data.source.remote.model.BaseModel
import com.john.themoviedb.search.model.Movie


class MovieRepository{
    private var dataLoader = RemoteDataLoader()
    private lateinit var results : MutableList<out BaseModel>

    fun loadAllMovies(sortBy: String, callback: DataSource.LoadMoviesCallback,
                      observableList : ObservableList<Movie>){
        results = dataLoader.loadAllMovies(sortBy, callback, observableList)
    }

}