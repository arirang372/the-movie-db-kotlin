package com.john.themoviedb.search

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.john.themoviedb.search.model.Movie
import com.john.themoviedb.search.view.SearchMoviesAdapter
import com.squareup.picasso.Picasso


object SearchMoviesBindingAdapters {
    @JvmStatic
    @BindingAdapter("app:movies")
    fun setMovieItems(recyclerView: RecyclerView, movies: MutableList<Movie>?) {
        var movieAdapter: SearchMoviesAdapter = recyclerView.adapter as SearchMoviesAdapter
        movieAdapter.setMovies(movies)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, src: String) {
        Picasso.get().load(src)
            .into(imageView)
    }
}