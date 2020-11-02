package com.john.themoviedb.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.john.themoviedb.R
import com.john.themoviedb.databinding.MovieListContentBinding
import com.john.themoviedb.search.callbacks.MovieItemListener
import com.john.themoviedb.search.model.Movie
import com.john.themoviedb.search.viewmodel.SearchMoviesViewModel
import com.squareup.picasso.Picasso
import java.util.*


class SearchMoviesAdapter(private var viewModel: SearchMoviesViewModel) :
    RecyclerView.Adapter<SearchMoviesAdapter.MovieViewHolder>() {
    private var movies: MutableList<Movie> = Collections.emptyList()

    class MovieViewHolder(binding: MovieListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val movieListItemBinding: MovieListContentBinding = binding
        fun cleanUp() {
            Picasso.get().cancelRequest(movieListItemBinding.thumbnailImageView)
            movieListItemBinding.thumbnailImageView.setImageBitmap(null)
            movieListItemBinding.titleTextView.visibility = View.GONE
        }
    }

    override fun getItemId(position: Int): Long {
        return RecyclerView.NO_ID
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: MovieListContentBinding =
            MovieListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = binding.root
        val gridNumberOfColumns = parent.context.resources.getInteger(R.integer.grid_number_cols)
        view.layoutParams.height = (parent.width / gridNumberOfColumns * 1.5f).toInt()
        return MovieViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieListItemBinding.model = movies[position]
        holder.movieListItemBinding.callback = object : MovieItemListener {
            override fun onItemClicked(movie: Movie) {
                viewModel.setMovieDetail(movie)
            }
        }
        holder.movieListItemBinding.executePendingBindings()
    }

    override fun onViewRecycled(holder: MovieViewHolder) {
        super.onViewRecycled(holder)
        holder.cleanUp()
    }

    fun setMovies(movies: MutableList<Movie>?) {
        movies?.let {
            this.movies = it
            notifyDataSetChanged()
        }
    }
}