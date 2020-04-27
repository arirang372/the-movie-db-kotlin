package com.john.themoviedb.details.callbacks

import com.john.themoviedb.search.model.Movie

interface MovieDetailListener {
    fun onMarkAsFavoriteButtonClicked(movie : Movie)

    fun onRemoveFromFavoriteButtonClicked(movie : Movie)
}