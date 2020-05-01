package com.john.themoviedb.common.viewModel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.john.networklib_livedata.ConnectivityStatus

open abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val dataLoading: ObservableBoolean = ObservableBoolean(false)

    fun setDataLoading(isLoading: Boolean) {
        dataLoading.set(isLoading)
    }

    fun setIsNetworkAvailable(connectivityStatus: ConnectivityStatus) {
        if ((connectivityStatus == ConnectivityStatus.OFFLINE
                    || connectivityStatus == ConnectivityStatus.WIFI_CONNECTED_HAS_NO_INTERNET
                    || connectivityStatus == ConnectivityStatus.UNKNOWN)
        ) {
            executeOnNetwork()
        } else {
            executeOnNotNetwork()
        }
    }

    abstract fun executeOnNetwork()

    abstract fun executeOnNotNetwork()
}