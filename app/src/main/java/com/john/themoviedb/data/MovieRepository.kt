package com.john.themoviedb.data

import com.john.themoviedb.data.source.remote.RemoteDataLoader
import com.john.themoviedb.data.source.remote.model.BaseModel


class MovieRepository{
    private var dataLoader = RemoteDataLoader()
    private lateinit var results : MutableList<BaseModel>


}