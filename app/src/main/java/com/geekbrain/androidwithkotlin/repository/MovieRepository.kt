package com.geekbrain.androidwithkotlin.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.geekbrain.androidwithkotlin.database.Movie
import com.geekbrain.androidwithkotlin.request.MovieApiClient
import com.geekbrain.androidwithkotlin.response.item

class MovieRepository private constructor(context: Context) {

    private val TAG = "MovieRepository"

    companion object {
        private var INSTANCE: MovieRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = MovieRepository(context)
            }
        }

        fun get(): MovieRepository {
            return INSTANCE ?: throw IllegalStateException("MovieRepository must be initialized")
        }

    }

    private var  movieApiClient = MovieApiClient.initialize()

    fun getTop250Data() : MutableLiveData<List<item>?>? {
        Log.i(TAG, "getTop250Data: ")
        return this.movieApiClient?.getMovies()
    }

}