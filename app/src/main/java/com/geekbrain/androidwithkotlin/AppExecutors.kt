package com.geekbrain.androidwithkotlin

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.geekbrain.androidwithkotlin.request.MovieApiClient
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class AppExecutors {
    companion object {
        private var INSTANCE: AppExecutors? = null

        fun initialize(): AppExecutors? {
            if (INSTANCE == null) {
                INSTANCE = AppExecutors()
            }
            return INSTANCE
        }

        fun get() : AppExecutors? {
            return INSTANCE
        }

    }

    private val _scheduledExecutorService : ScheduledExecutorService = Executors.newScheduledThreadPool(3)

    public val scheduledExecutorService = _scheduledExecutorService

}