package com.geekbrain.androidwithkotlin.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.geekbrain.androidwithkotlin.database.Movie
import com.geekbrain.androidwithkotlin.request.MovieApiClient

class MovieRepository private constructor(context: Context) {

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

    fun getTop250Data() : MutableLiveData<List<Movie>>? {
        return this.movieApiClient?.getMovies()
    }

}