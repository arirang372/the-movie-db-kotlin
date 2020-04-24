package com.john.themoviedb.data.source

import com.john.themoviedb.search.model.Movie


interface DataSource {
    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies: MutableList<Movie>)
        fun onMovieNotAvailable()
    }

    interface LoadReviewsTrailersCallback {
        fun onReviewsTrailersLoaded(reviewsTrailers: MutableList<Comparable<*>>)

        fun onReviewsTrailersNotAvailable()
    }

    interface GetMovieCallback {
        fun onMovieLoaded(movie: Movie)
        fun onDataNotAvailable()
    }

    fun loadAllMovies(sortBy: String, callback: LoadMoviesCallback)

    fun getMovie(id: Long, callback: GetMovieCallback)

    fun saveMovie(movie: Movie)

    fun deleteMovie(id: Long)
}