package com.geekbrain.androidwithkotlin.request

import com.geekbrain.androidwithkotlin.response.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("en/API/Top250Movies/{apiKey}")
    fun getTop250Movies(
        @Path("apiKey") key: String?
    ): retrofit2.Call<MovieSearchResponse>
}