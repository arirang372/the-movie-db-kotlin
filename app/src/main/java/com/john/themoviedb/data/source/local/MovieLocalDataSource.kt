package com.john.themoviedb.data.source.local

import com.john.themoviedb.data.source.DataSource
import com.john.themoviedb.search.model.Movie
import com.john.themoviedb.utils.AppExecutors


class MovieLocalDataSource(
    appExecutors: AppExecutors, movieDao: MovieDao
) : DataSource {
    private val mAppExecutors = appExecutors
    private val mMovieDao = movieDao

    companion object {
        private var instance: MovieLocalDataSource? = null

        fun getInstance(
            appExecutors: AppExecutors, movieDao: MovieDao
        ): MovieLocalDataSource {
            synchronized(this) {
                if (instance == null) {
                    instance = MovieLocalDataSource(appExecutors, movieDao)
                }
            }
            return instance!!
        }
    }

    override fun loadAllMovies(sortBy: String, callback: DataSource.LoadMoviesCallback) {
        mAppExecutors.diskIO().execute {
            var movies = mMovieDao.getMovies()
            mAppExecutors.mainThread().execute {
                    callback.onMoviesLoaded(movies)
            }
        }
    }

    override fun getMovie(id: Long, callback: DataSource.GetMovieCallback) {
        mAppExecutors.diskIO().execute {
            var movie = mMovieDao.getMovie(id)
            mAppExecutors.mainThread().execute {
                if (movie != null) {
                    callback.onMovieLoaded(movie)
                } else {
                    callback.onDataNotAvailable()
                }
            }
        }
    }

    override fun saveMovie(movie: Movie) {
        mAppExecutors.diskIO().execute {
            mMovieDao.insertMovie(movie)
        }
    }

    override fun deleteMovie(id: Long) {
        mAppExecutors.diskIO().execute {
            mMovieDao.deleteMovie(id)
        }
    }
}