package com.geekbrain.androidwithkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrain.androidwithkotlin.database.Movie
import com.geekbrain.androidwithkotlin.repository.MovieRepository

class MainViewModel() : ViewModel() {

    private val TAG = "MainViewModel"

    private val movieRepository = MovieRepository.get()


    fun getTop250Data(): MutableLiveData<List<Movie>?>? {
        Log.i(TAG, "getTop250Data: ")
        return movieRepository.getTop250Data()
    }


}



