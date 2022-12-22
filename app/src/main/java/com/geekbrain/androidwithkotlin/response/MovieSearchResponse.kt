package com.geekbrain.androidwithkotlin.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//This class for TOp250 request
class MovieSearchResponse {
    // 1. Finding movie object
    @SerializedName("items")
    @Expose
    private lateinit var items: List<MovieItem>

    fun getMovies(): List<MovieItem>?{
        return items
    }

    override fun toString(): String {
        return "MovieSearchResponse(movies=$items)"
    }

}