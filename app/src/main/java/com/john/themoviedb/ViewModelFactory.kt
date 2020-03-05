package com.john.themoviedb

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.john.themoviedb.data.MovieRepository
import com.john.themoviedb.search.viewmodel.SearchMoviesViewModel


class ViewModelFactory(application: Application) : ViewModelProvider.NewInstanceFactory() {
    private var mApplication = application
    private var mRepository: MovieRepository = MovieRepository()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchMoviesViewModel::class.java)) {
            return SearchMoviesViewModel(mApplication, mRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)
    }

    companion object {
        private var instance: ViewModelFactory? = null
        fun getInstance(application: Application): ViewModelFactory {
            if (instance == null)
                instance = ViewModelFactory(application)
            return instance as ViewModelFactory
        }
    }
}