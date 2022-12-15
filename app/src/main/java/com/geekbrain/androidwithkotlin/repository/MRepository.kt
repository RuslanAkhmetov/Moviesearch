package com.geekbrain.androidwithkotlin.repository

import androidx.lifecycle.MutableLiveData
import com.geekbrain.androidwithkotlin.database.Movie

interface MRepository {
    fun getTop250Data(): MutableLiveData<List<Movie>>
}