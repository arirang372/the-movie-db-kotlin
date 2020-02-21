package com.john.themoviedb.data.source.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("/3/movie/{sort_by}")
    fun getMovies(@Path("sort_by") sortBy: String, @Query("api_key") apiKey: String)

    @GET("/3/movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: Long, @Query("api_key") apiKey: String)

    @GET("/3/movie/{id}/videos")
    fun getMovieTrailers(@Path("id") id: Long, @Query("api_key") apiKey: String)
}