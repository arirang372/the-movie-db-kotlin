package com.john.themoviedb.details.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.john.themoviedb.data.MovieRepository


class DetailMovieViewModel(application: Application, repository: MovieRepository) :
    AndroidViewModel(application) {

    private val mRepository: MovieRepository = repository


}