package com.john.themoviedb

import android.content.Context
import com.john.themoviedb.data.MovieRepository
import com.john.themoviedb.data.source.local.MovieDatabase
import com.john.themoviedb.data.source.local.MovieLocalDataSource
import com.john.themoviedb.data.source.remote.MovieRemoteDataSource
import com.john.themoviedb.utils.AppExecutors


class Injection {

    companion object {
        fun provideMovieRepository(context: Context): MovieRepository {
            var database: MovieDatabase = MovieDatabase.getInstance(context)
            return MovieRepository(
                MovieRemoteDataSource(),
                MovieLocalDataSource.getInstance(AppExecutors(), database.movieDao())
            )
        }
    }
}