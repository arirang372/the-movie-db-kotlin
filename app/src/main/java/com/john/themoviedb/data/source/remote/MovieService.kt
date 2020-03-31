package com.john.themoviedb.data.source.remote

import com.john.themoviedb.data.source.remote.model.ApiResponse
import com.john.themoviedb.data.source.remote.model.BaseApiResponse
import com.john.themoviedb.details.model.Review
import com.john.themoviedb.details.model.Trailer
import com.john.themoviedb.search.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("/3/movie/{sort_by}")
    fun getMovies(
        @Path("sort_by") sortBy: String,
        @Query("api_key") apiKey: String
    ): Observable<BaseApiResponse<Movie>>

    @GET("/3/movie/{id}/reviews")
    fun getMovieReviews(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ): Observable<ApiResponse<Review>>

    @GET("/3/movie/{id}/videos")
    fun getMovieTrailers(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ): Observable<ApiResponse<Trailer>>
}