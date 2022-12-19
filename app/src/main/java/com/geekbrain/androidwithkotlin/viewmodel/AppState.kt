package com.geekbrain.androidwithkotlin.viewmodel

import com.geekbrain.androidwithkotlin.response.MovieItem

sealed class AppState{
    data class Success (val movieData: List<MovieItem>?) : AppState()
    data class Error (val error: Throwable) : AppState()
    object Loading : AppState()
}
