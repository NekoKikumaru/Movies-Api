package com.ptzkg.moviesapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ptzkg.moviesapi.api.MovieApi
import com.ptzkg.moviesapi.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {

    var result: MutableLiveData<Movie> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var loadError: MutableLiveData<Boolean> = MutableLiveData()

    fun getResult(): LiveData<Movie> = result
    fun getLoading(): LiveData<Boolean> = loading
    fun getLoadError(): LiveData<Boolean> = loadError

    private val movieApi: MovieApi = MovieApi()

    fun loadResult(id: Int) {
        loading.value = true

        val call = movieApi.getMovieByID(id)
        call.enqueue(object: Callback<Movie> {

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                loadError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                response.isSuccessful.let {
                    loading.value = false
                    result.value = response.body()
                }
            }

        })
    }
}