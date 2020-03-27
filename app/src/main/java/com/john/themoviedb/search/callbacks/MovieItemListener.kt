package com.john.themoviedb.search.callbacks

import com.john.themoviedb.search.model.Movie

interface MovieItemListener {
    fun onItemClicked(movie: Movie)
}