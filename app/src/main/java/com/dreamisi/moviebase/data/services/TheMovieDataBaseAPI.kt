package com.dreamisi.moviebase.data.services

import com.dreamisi.moviebase.data.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Header

private const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5N2UyOWI4MTBhNGZlM2VlNDVjMTU5MDRkYWI1ZTA1MiIsInN1YiI6IjY1ZjViYzA5ZTAzOWYxMDE2NDAzNmJhZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JHAmBBmB33R3roiGUj2f_wgrMbS3Cu2wykS2f0ogxKw"

interface TheMovieDataBaseAPI {

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopularMoviesList(
        @Header("Authorization") apiKey: String = "Bearer $API_KEY"
    ): PopularMovieResponse
    }
