package com.john.themoviedb.common.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.john.networklib_livedata.ConnectivityStatus
import com.john.networklib_livedata.NetworkEvents

open abstract class BaseFragment : Fragment() {
    private lateinit var events: NetworkEvents
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        events = NetworkEvents(activity)
        events.enableWifiScan()
        events.networkConnectionChangedEvent.observe(this, Observer {
            setUpIsNetworkAvailable(it)
        })
    }

    abstract fun setUpIsNetworkAvailable(connectivityStatus: ConnectivityStatus)


    override fun onStart() {
        super.onStart()
        events.register()
    }

    override fun onStop() {
        super.onStop()
        events.unregister()
    }
}