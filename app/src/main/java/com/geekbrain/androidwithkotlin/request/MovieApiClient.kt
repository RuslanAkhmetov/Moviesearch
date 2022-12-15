package com.geekbrain.androidwithkotlin.request

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.geekbrain.androidwithkotlin.AppExecutors
import com.geekbrain.androidwithkotlin.database.Movie
import com.geekbrain.androidwithkotlin.response.MovieSearchResponse
import com.geekbrain.androidwithkotlin.utils.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class MovieApiClient {

    private val TAG = "MovieApiClient"
    companion object {
        private var INSTANCE: MovieApiClient? = null

        fun initialize(): MovieApiClient? {
            if (INSTANCE == null) {
                INSTANCE = MovieApiClient()
                INSTANCE!!.getRetrofitResponse()
            }
            return INSTANCE
        }

        fun get(): MovieApiClient? {
            return INSTANCE
        }


    }

    private var movies: MutableLiveData<List<Movie>> = MutableLiveData()



    fun getMovies(): MutableLiveData<List<Movie>> {
        return this.movies
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

    private fun getRetrofitResponse() {
        val movieApi = Service.getMovieApi()

        val  responseCall : Call<MovieSearchResponse> = movieApi
            .getTop25oMovies()    //Credentials.API_KEY)

/*        val retrofit = Retrofit.Builder()
            .baseUrl("https://imdb-api.com/en/API/Top250Movies/k_jnyqw63u")
            .build()*/


        responseCall.enqueue(object : Callback<MovieSearchResponse> {
            override fun onResponse(
                call: Call<MovieSearchResponse>,
                response: Response<MovieSearchResponse>
            ) {
                if(response.code() == 200){

                    Log.i(TAG, "onResponse: ${response.raw()}" )
                    Log.i(TAG, "onResponse: ${response.body()}" )
                    Log.i(TAG, "onResponse: ${response.headers()}" )

                    movies.postValue(response.body()?.getMovie())
                }
            }

            override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }

}

// Retrieving data from RestAPI by runnable class
private class RetrieveMoviesRunnable : Runnable {


    override fun run() {

    }
}





