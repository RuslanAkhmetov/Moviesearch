package com.geekbrain.androidwithkotlin.request

import com.geekbrain.androidwithkotlin.response.MovieSearchResponse
import okhttp3.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieApi {
    @GET("en/API/Top250Movies/k_jnyqw63u")
    fun getTop25oMovies(
        //@Query("_apiKey") key: String?
    ): retrofit2.Call<MovieSearchResponse>
}