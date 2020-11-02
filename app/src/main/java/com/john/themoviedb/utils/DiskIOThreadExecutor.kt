package com.john.themoviedb.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors


class DiskIOThreadExecutor : Executor {
    private val mDiskIO: Executor = Executors.newSingleThreadExecutor()
    override fun execute(command: Runnable) {
        mDiskIO.execute(command)
    }
}