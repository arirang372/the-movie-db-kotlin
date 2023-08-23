package com.john.themoviedb.data.source.remote

import android.annotation.SuppressLint
import androidx.databinding.ObservableList
import com.john.themoviedb.BuildConfig
import com.john.themoviedb.data.source.DataSource
import com.john.themoviedb.data.source.remote.model.ApiResponse
import com.john.themoviedb.data.source.remote.model.BaseApiResponse
import com.john.themoviedb.details.model.Category
import com.john.themoviedb.details.model.Review
import com.john.themoviedb.details.model.Trailer
import com.john.themoviedb.search.model.Movie
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MovieRemoteDataSource : DataSource {
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
        private const val BASE_URL = "https://api.themoviedb.org/"
        private const val POSTER_IMAGE_URL_BASE = "http://image.tmdb.org/t/p/w342%s"
        private const val BACK_DROP_IMAGE_URL_BASE = "http://image.tmdb.org/t/p/original%s"
        private const val YOUTUBE_VIDEO_URL_BASE = "http://www.youtube.com/watch?v=%s"
        private const val YOUTUBE_IMAGE_URL_BASE = "http://img.youtube.com/vi/%s/0.jpg"
    }

    override fun loadAllMovies(
        sortBy: String,
        callback: DataSource.LoadMoviesCallback
    ) {
        service.getMovies(sortBy, BuildConfig.THE_MOVIE_DATABASE_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response: BaseApiResponse<Movie> -> response.results }
            .map { createImageUrls(it) }
            .subscribe(object : Observer<MutableList<Movie>> {
                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    callback.onMovieNotAvailable()
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(movies: MutableList<Movie>) {
                    callback.onMoviesLoaded(movies)
                }
            })
    }

    @SuppressLint("CheckResult")
    fun loadReviewsAndTrailers(
        movieId: Long,
        callback: DataSource.LoadReviewsTrailersCallback
    ) {
        Observable.zip(getMovieTrailers(movieId = movieId), getMovieReviews(movieId)
        ) { trailers, reviews ->
            var combinedList = mutableListOf<Comparable<*>>()
            combinedList.add(Category("Video"))
            combinedList.addAll(createTrailerUrls(trailers.results, movieId))
            combinedList.add(Category("Reviews"))
            combinedList.addAll(appendMovieIdsOnReviews(reviews.results, movieId))
            combinedList
        }.subscribe(object : Observer<MutableList<Comparable<*>>> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(reviewsAndTrailers: MutableList<Comparable<*>>) {
                callback.onReviewsTrailersLoaded(reviewsAndTrailers)
            }

            override fun onError(e: Throwable) {
                callback.onReviewsTrailersNotAvailable()
            }
        })
    }

    private fun createTrailerUrls(
        trailers: MutableList<Trailer>,
        movieId: Long
    ): MutableList<Trailer> {
        for (trailer in trailers) {
            trailer.movieId = movieId
            trailer.trailerImageUrl = String.format(YOUTUBE_IMAGE_URL_BASE, trailer.key)
            trailer.trailerVideoUrl = String.format(YOUTUBE_VIDEO_URL_BASE, trailer.key)
        }
        return trailers
    }

    private fun appendMovieIdsOnReviews(
        reviews: MutableList<Review>,
        movieId: Long
    ): MutableList<Review> {
        for (review in reviews) {
            review.movieId = movieId
        }
        return reviews
    }

    private fun getMovieReviews(movieId: Long): Observable<ApiResponse<Review>> {
        return service.getMovieReviews(movieId, BuildConfig.THE_MOVIE_DATABASE_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getMovieTrailers(movieId: Long): Observable<ApiResponse<Trailer>> {
        return service.getMovieTrailers(movieId, BuildConfig.THE_MOVIE_DATABASE_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getConvertedReleaseDate(movieReleaseDate: String?): String {
        var simpleDateFormat = SimpleDateFormat("yyyy-dd-MM", Locale.US)
        return movieReleaseDate.let {
            try {
                var date = simpleDateFormat.parse(it)
                DateFormat.getDateInstance().format(date)
            } catch (e: ParseException) { //do nothing. }
                "null release date"
            }
        }
    }

    private fun createImageUrls(movies: MutableList<Movie>): MutableList<Movie> {
        for (m in movies) {
            m.poster_path = String.format(POSTER_IMAGE_URL_BASE, m.poster_path)
            m.backdrop_path = String.format(BACK_DROP_IMAGE_URL_BASE, m.backdrop_path)
            m.release_date = getConvertedReleaseDate(m.release_date)
            m.rating = m.vote_average?.let { it.toFloat() }!!
            m.rating = m.rating / 2
        }
        return movies
    }

    override fun getMovie(id: Long, callback: DataSource.GetMovieCallback) {

    }

    override fun saveMovie(movie: Movie) {
    }

    override fun deleteMovie(id: Long) {
    }
}