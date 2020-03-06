package com.john.themoviedb.data.source.remote

import androidx.databinding.ObservableList
import com.john.themoviedb.BuildConfig
import com.john.themoviedb.data.source.DataSource
import com.john.themoviedb.data.source.remote.model.BaseApiResponse
import com.john.themoviedb.search.model.Movie
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
        private const val BASE_URL = "https://api.themoviedb.org/"
        private const val POSTER_IMAGE_URL_BASE = "http://image.tmdb.org/t/p/w342%s"
        private const val BACK_DROP_IMAGE_URL_BASE = "http://image.tmdb.org/t/p/original%s"
        private const val YOUTUBE_TRAILER_URL_BASE = "http://www.youtube.com/watch?v="
    }

    fun loadAllMovies(
        sortBy: String,
        callback: DataSource.LoadMoviesCallback,
        observableList: ObservableList<Movie>
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
                    observableList.clear()
                    observableList.addAll(movies)
                    callback.onMoviesLoaded()
                }
            })
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
        }
        return movies
    }
}