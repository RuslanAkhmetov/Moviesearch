package com.geekbrain.androidwithkotlin.request

import androidx.lifecycle.MutableLiveData
import com.geekbrain.androidwithkotlin.AppExecutors
import com.geekbrain.androidwithkotlin.response.MovieSearchResponse
import com.geekbrain.androidwithkotlin.viewmodel.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class MovieApiClient {

    private val TAG = "MovieApiClient"

    companion object {
        private var INSTANCE: MovieApiClient? = null

        fun initialize(): MovieApiClient? {

            return INSTANCE?:MovieApiClient().apply {
                getRetrofitResponse()
            }
        }

        fun get(): MovieApiClient? {
            return INSTANCE
        }


    }

    private var movies: MutableLiveData<AppState> = MutableLiveData()

    fun getMovies(): MutableLiveData<AppState> = this.movies

    private fun getRetrofitResponse() {
        val movieApi = Service.getMovieApi()

        val  responseCall : Call<MovieSearchResponse> = movieApi
                                                        .getTop25oMovies()

        responseCall.enqueue(object : Callback<MovieSearchResponse> {
            override fun onResponse(
                call: Call<MovieSearchResponse>,
                response: Response<MovieSearchResponse>
            ) {
                if(response.code() == 200){
                    movies.postValue(AppState.Success(response.body()?.getMovies()))
                } else{
                    movies.postValue(AppState.Loading)
                }
            }

            override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
                movies.postValue(AppState.Error(java.lang.RuntimeException("Can't load data")))
            }

        })
    }

}

fun searchMoviesApi() {
    val myHandler = AppExecutors.get()?.scheduledExecutorService?.submit(Runnable {
        run {
            //Retrieve Data from API
        }
    })

    AppExecutors.get()?.scheduledExecutorService?.schedule(Runnable {
        run {
            //Cancelling the retrofit code
            myHandler?.cancel(true)
        }
    }, 5000, TimeUnit.MICROSECONDS)
}

// Retrieving data from RestAPI by runnable class
private class RetrieveMoviesRunnable : Runnable {


    override fun run() {

    }
}





