package com.john.themoviedb.data.source.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataLoader {
    private var service: MovieService

    init {
        var retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        service = retrofit.create(MovieService::class.java)
    }


    companion object {
        private const val BASE_URL = "https://api.flickr.com"

    }

}