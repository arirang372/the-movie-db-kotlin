package com.john.themoviedb.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors {
    companion object {
        private const val THREAD_COUNT: Int = 3
    }

    private val mDiskIO: Executor
    private val mNetworkIO: Executor
    private val mMainThread: Executor

    init {
        mDiskIO = DiskIOThreadExecutor()
        mNetworkIO = Executors.newFixedThreadPool(THREAD_COUNT)
        mMainThread = MainThreadExecutor()
    }

    fun diskIO(): Executor {
        return mDiskIO
    }

    fun networkIO(): Executor {
        return mNetworkIO
    }

    fun mainThread(): Executor {
        return mMainThread
    }
}