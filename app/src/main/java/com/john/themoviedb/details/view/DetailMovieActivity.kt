package com.john.themoviedb.details.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.john.themoviedb.R
import com.john.themoviedb.databinding.ActivityDetailMovieBinding
import com.john.themoviedb.search.model.Movie


class DetailMovieActivity : AppCompatActivity() {
    private lateinit var detailMovieActivityBinding: ActivityDetailMovieBinding

    companion object {
        const val ARG_MOVIE = "movie"
        fun getIntent(context: Context, movie: Movie): Intent {
            var intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(ARG_MOVIE, movie)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)
        detailMovieActivityBinding.model = intent.getParcelableExtra(ARG_MOVIE)
        setUpActionBar()
        if (savedInstanceState == null) {
            var fragment = DetailMovieFragment.newInstance(intent.getParcelableExtra(ARG_MOVIE))
            supportFragmentManager.beginTransaction().add(R.id.movie_detail_container, fragment)
                .commit()
        }
        detailMovieActivityBinding.executePendingBindings()
    }

    private fun setUpActionBar() {
        var toolbar: Toolbar? = findViewById(R.id.detail_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}