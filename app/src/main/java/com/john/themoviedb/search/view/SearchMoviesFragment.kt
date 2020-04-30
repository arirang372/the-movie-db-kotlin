package com.john.themoviedb.search.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.networklib_livedata.NetworkEvents
import com.john.themoviedb.R
import com.john.themoviedb.data.MovieConstants
import com.john.themoviedb.databinding.FragmentSearchMoviesBinding
import com.john.themoviedb.search.viewmodel.SearchMoviesViewModel


class SearchMoviesFragment : Fragment() {
    private lateinit var searchMoviesFragmentBinding: FragmentSearchMoviesBinding
    private var viewModel: SearchMoviesViewModel? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var events: NetworkEvents
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let {
            SearchMoviesActivity.obtainViewModel(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        events = NetworkEvents(activity)
        events.enableWifiScan()
        setUpNetworkChangedEvent()
    }

    private fun setUpNetworkChangedEvent() {
        events.networkConnectionChangedEvent.observe(this, Observer {
            viewModel?.setIsNetworkAvailable(it)
        })
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
        events.register()
        //refresh the favorite
        viewModel?.updateFavoriteMovies()
    }

    override fun onStop() {
        super.onStop()
        events.unregister()
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
                menu?.let { it.findItem(R.id.sort_by_popular).setCheckable(true) }

            MovieConstants.SortBy.TOP_RATED ->
                menu?.let { it.findItem(R.id.sort_by_top_rated).setCheckable(true) }

            MovieConstants.SortBy.FAVORITES ->
                menu?.let { it.findItem(R.id.sort_by_favorite).setCheckable(true) }
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel?.loadAllMovies(item.itemId)

        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }
}