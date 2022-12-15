package com.geekbrain.androidwithkotlin.response

import com.geekbrain.androidwithkotlin.database.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//This class for TOp250 request
class MovieSearchResponse {
    // 1. Finding movie object
    @SerializedName("Top250Data")
    @Expose
    private  var movies: List<Movie> = listOf<Movie>()

    fun getMovie(): List<Movie>{
        return movies
    }

    override fun toString(): String {
        return "MovieSearchResponse(movies=$movies)"
    }


}