package com.john.themoviedb.common.viewModel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val dataLoading: ObservableBoolean = ObservableBoolean(false)

    fun setDataLoading(isLoading: Boolean) {
        dataLoading.set(isLoading)
    }
}