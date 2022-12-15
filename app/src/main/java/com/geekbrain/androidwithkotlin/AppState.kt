package com.geekbrain.androidwithkotlin

import com.geekbrain.androidwithkotlin.database.Movie

sealed class AppState{
    data class Success (val movieData: List<Movie>) : AppState()
    data class Error (val error: Throwable) : AppState()
    object Loading : AppState()
}
