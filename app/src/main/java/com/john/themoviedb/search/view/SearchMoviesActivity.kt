package com.john.themoviedb.search.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.john.themoviedb.R
import com.john.themoviedb.ViewModelFactory
import com.john.themoviedb.databinding.ActivitySearchMoviesBinding
import com.john.themoviedb.search.viewmodel.SearchMoviesViewModel


class SearchMoviesActivity : AppCompatActivity() {
    private lateinit var searchMoviesActivityBinding: ActivitySearchMoviesBinding

    companion object {
        fun obtainViewModel(activity: FragmentActivity): SearchMoviesViewModel {
            var factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(SearchMoviesViewModel::class.java)
        }
    }

    lateinit var viewModel: SearchMoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel(this)
        searchMoviesActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search_movies)
        supportFragmentManager.beginTransaction()
            .add(R.id.movie_main_container, SearchMoviesFragment())
            .commit()
        searchMoviesActivityBinding.executePendingBindings()
    }
}