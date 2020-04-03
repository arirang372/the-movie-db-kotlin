package com.john.themoviedb.details.callbacks

import com.john.themoviedb.details.model.Trailer


interface MovieTrailerItemListener {
    fun onTrailerItemClicked(trailer: Trailer)
}