package com.john.themoviedb.search.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.themoviedb.R
import com.john.themoviedb.data.MovieConstants
import com.john.themoviedb.databinding.FragmentSearchMoviesBinding
import com.john.themoviedb.search.viewmodel.SearchMoviesViewModel


class SearchMoviesFragment : Fragment() {
    private lateinit var searchMoviesFragmentBinding: FragmentSearchMoviesBinding
    private var viewModel: SearchMoviesViewModel? = null
    var sortBy: String = MovieConstants.SortBy.MOST_POPULAR
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let {
            SearchMoviesActivity.obtainViewModel(it)
        }
        viewModel?.loadAllMovies(sortBy = sortBy)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchMoviesFragmentBinding = FragmentSearchMoviesBinding.inflate(
            LayoutInflater.from(context),
            container, false
        )
        setHasOptionsMenu(true)
        return searchMoviesFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchMoviesFragmentBinding.viewModel = viewModel
        recyclerView = view.findViewById(R.id.movie_list)
        recyclerView.layoutManager =
            GridLayoutManager(activity, resources.getInteger(R.integer.grid_number_cols))
        recyclerView.adapter = viewModel?.let { SearchMoviesAdapter(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity, menu)
        when (sortBy) {
            MovieConstants.SortBy.MOST_POPULAR ->
                menu?.let { it.findItem(R.id.sort_by_popular).setCheckable(true) }

            MovieConstants.SortBy.TOP_RATED ->
                menu?.let { it.findItem(R.id.sort_by_top_rated).setCheckable(true) }

            MovieConstants.SortBy.FAVORITES ->
                menu?.let { it.findItem(R.id.sort_by_favorite).setCheckable(true) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_by_popular -> {

            }
        }

        return true
    }
}