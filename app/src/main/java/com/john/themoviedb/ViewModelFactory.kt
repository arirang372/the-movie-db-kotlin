package com.john.themoviedb

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.john.themoviedb.data.MovieRepository
import com.john.themoviedb.details.viewmodel.DetailMovieViewModel
import com.john.themoviedb.search.viewmodel.SearchMoviesViewModel


class ViewModelFactory(application: Application, repository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {
    private var mApplication = application
    private var mRepository: MovieRepository = repository

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchMoviesViewModel::class.java)) {
            return SearchMoviesViewModel(mApplication, mRepository) as T
        } else if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)) {
            return DetailMovieViewModel(mApplication, mRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)
    }

    companion object {
        private var instance: ViewModelFactory? = null
        fun getInstance(application: Application): ViewModelFactory {
            if (instance == null)
                instance = ViewModelFactory(
                    application,
                    Injection.provideMovieRepository(application.applicationContext)
                )
            return instance as ViewModelFactory
        }
    }
}