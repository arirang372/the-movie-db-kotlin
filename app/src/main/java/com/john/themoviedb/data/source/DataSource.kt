package com.john.themoviedb.data.source


interface DataSource
{
    interface LoadMoviesCallback
    {
        fun onMoviesLoaded()

        fun onMovieNotAvailable()
    }
}