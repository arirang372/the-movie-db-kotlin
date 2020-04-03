package com.john.themoviedb.details.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.themoviedb.R
import com.john.themoviedb.ViewModelFactory
import com.john.themoviedb.databinding.FragmentDetailMovieBinding
import com.john.themoviedb.details.callbacks.MovieReviewItemListener
import com.john.themoviedb.details.callbacks.MovieTrailerItemListener
import com.john.themoviedb.details.model.Review
import com.john.themoviedb.details.model.Trailer
import com.john.themoviedb.details.view.DetailMovieActivity.Companion.ARG_MOVIE
import com.john.themoviedb.details.viewmodel.DetailMovieViewModel
import com.john.themoviedb.search.model.Movie

class DetailMovieFragment : Fragment(), MovieTrailerItemListener, MovieReviewItemListener {
    private lateinit var detailMovieFragmentBinding: FragmentDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel

    companion object {
        fun newInstance(movie: Movie): DetailMovieFragment {
            var movieDetail = DetailMovieFragment()
            var args = Bundle()
            args.putParcelable(ARG_MOVIE, movie)
            movieDetail.arguments = args
            return movieDetail
        }
    }

    private fun obtainViewModel(): DetailMovieViewModel {
        var factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return ViewModelProviders.of(this!!.activity!!, factory)
            .get(DetailMovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = obtainViewModel()
        var movie = arguments?.let { it.getParcelable<Movie>(ARG_MOVIE) }
        movie?.let { viewModel.loadMovieTrailersAndReviews(it.id) }
        detailMovieFragmentBinding =
            FragmentDetailMovieBinding.inflate(LayoutInflater.from(context), container, false)
        detailMovieFragmentBinding.model = movie
        detailMovieFragmentBinding.viewModel = viewModel
        var recyclerView =
            detailMovieFragmentBinding.root.findViewById<RecyclerView>(R.id.list_item_recycler_view)
        recyclerView.adapter = DetailMovieAdapter(this, this, viewModel)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return detailMovieFragmentBinding.root
    }

    override fun onTrailerItemClicked(trailer: Trailer) {
        trailer.key?.let { openUri(it) }
    }

    override fun onReviewClicked(review: Review) {
        review.url?.let { openUri(it) }
    }

    private fun openUri(uri: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
    }
}