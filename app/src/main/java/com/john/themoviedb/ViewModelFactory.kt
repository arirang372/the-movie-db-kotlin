package com.john.themoviedb

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.john.themoviedb.data.MovieRepository


class ViewModelFactory(application: Application) : ViewModelProvider.NewInstanceFactory()
{
    private var mApplication = application
    private var mRepository : MovieRepository = MovieRepository()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }
}