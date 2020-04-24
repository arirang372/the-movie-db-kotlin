package com.john.themoviedb.data

import com.john.themoviedb.data.source.DataSource
import com.john.themoviedb.data.source.local.MovieLocalDataSource
import com.john.themoviedb.data.source.remote.MovieRemoteDataSource
import com.john.themoviedb.search.model.Movie


class MovieRepository(
    movieRemoteDataSource: MovieRemoteDataSource,
    movieLocalDataSource: MovieLocalDataSource
) : DataSource {

    private val mMovieRemoteDataSource = movieRemoteDataSource
    private val mMovieLocalDataSource = movieLocalDataSource

    override fun loadAllMovies(
        sortBy: String, callback: DataSource.LoadMoviesCallback
    ) {
        if (!sortBy.contentEquals(MovieConstants.SortBy.FAVORITES))
            mMovieRemoteDataSource.loadAllMovies(sortBy, callback)
        else
            mMovieLocalDataSource.loadAllMovies(sortBy, callback)
    }

    fun loadReviewsAndTrailers(
        movieId: Long,
        callback: DataSource.LoadReviewsTrailersCallback
    ) {
        mMovieRemoteDataSource.loadReviewsAndTrailers(movieId, callback)
    }

    override fun getMovie(id: Long, callback: DataSource.GetMovieCallback){
        mMovieLocalDataSource.getMovie(id, callback)
    }

    override fun saveMovie(movie: Movie) {
        mMovieLocalDataSource.saveMovie(movie)
    }

    override fun deleteMovie(id: Long) {
        mMovieLocalDataSource.deleteMovie(id)
    }
}