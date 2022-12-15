package com.geekbrain.androidwithkotlin

import android.app.Application
import com.geekbrain.androidwithkotlin.repository.MovieRepository

class MovieSearchApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        MovieRepository.initialize(this)
    }
}