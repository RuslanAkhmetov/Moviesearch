package com.geekbrain.androidwithkotlin.request

import com.geekbrain.androidwithkotlin.utils.Credentials
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {
    companion object{
        private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        private val retrofit = retrofitBuilder.build()

        private val movieApi: MovieApi = retrofit.create(MovieApi::class.java)

        fun getMovieApi(): MovieApi = movieApi

    }

}