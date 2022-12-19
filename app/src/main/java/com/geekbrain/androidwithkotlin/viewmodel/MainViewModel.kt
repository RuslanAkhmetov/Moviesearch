package com.geekbrain.androidwithkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrain.androidwithkotlin.repository.MovieRepository

class MainViewModel() : ViewModel() {

    private var liveDateToObserve: MutableLiveData<AppState>? = MutableLiveData()

    private val TAG = "MainViewModel"

    private val movieRepository = MovieRepository.get()

    fun getTop250Data(): MutableLiveData<AppState>?{ //MutableLiveData<List<MovieItem>> {
        Log.i(TAG, "getTop250Data: ")
        return movieRepository.getTop250Data()
    }

    fun getMovies() = getDataFromRemoteSource()

    private fun getDataFromRemoteSource() {
        liveDateToObserve = getTop250Data()

    }


}



