package com.john.themoviedb.search.view

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.networklib_livedata.ConnectivityStatus
import com.john.themoviedb.R
import com.john.themoviedb.common.ui.BaseFragment
import com.john.themoviedb.data.MovieConstants
import com.john.themoviedb.databinding.FragmentSearchMoviesBinding
import com.john.themoviedb.search.viewmodel.SearchMoviesViewModel


class SearchMoviesFragment : BaseFragment() {
    private lateinit var searchMoviesFragmentBinding: FragmentSearchMoviesBinding
    private var viewModel: SearchMoviesViewModel? = null
    private lateinit var recyclerView: RecyclerView

    override fun setUpIsNetworkAvailable(connectivityStatus: ConnectivityStatus) {
        viewModel?.setIsNetworkAvailable(connectivityStatus)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let {
            SearchMoviesActivity.obtainViewModel(it)
        }
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


    override fun onStart() {
        super.onStart()
        //refresh the favorite
        viewModel?.updateFavoriteMovies()
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
        menu.clear()
        inflater.inflate(R.menu.main_activity, menu)
        when (viewModel?.sortByField?.get()) {
            MovieConstants.SortBy.MOST_POPULAR ->
                menu.findItem(R.id.sort_by_popular).isCheckable = true

            MovieConstants.SortBy.TOP_RATED ->
                menu.findItem(R.id.sort_by_top_rated).isCheckable = true

            MovieConstants.SortBy.FAVORITES ->
                menu.findItem(R.id.sort_by_favorite).isCheckable = true
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel?.loadAllMovies(item.itemId)

        return true
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

}