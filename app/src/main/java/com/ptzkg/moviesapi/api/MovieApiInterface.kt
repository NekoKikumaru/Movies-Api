package com.ptzkg.moviesapi.api

import com.ptzkg.moviesapi.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") api_key: String
    ): Call<Movie>

}