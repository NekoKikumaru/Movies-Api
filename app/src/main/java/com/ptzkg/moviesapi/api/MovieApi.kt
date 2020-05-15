package com.ptzkg.moviesapi.api

import com.ptzkg.moviesapi.model.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    private val movieApiInterface: MovieApiInterface

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val api_Key = "186afbd4a6bf37fb67270160c4525761"
    }

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        movieApiInterface = retrofit.create(MovieApiInterface::class.java)
    }

    fun getTopRatedMovies(): Call<Movie> {
        return movieApiInterface.getTopRatedMovies("186afbd4a6bf37fb67270160c4525761")
    }
}