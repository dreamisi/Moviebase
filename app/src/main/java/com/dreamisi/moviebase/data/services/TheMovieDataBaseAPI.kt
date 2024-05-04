package com.dreamisi.moviebase.data.services

import com.dreamisi.moviebase.data.JsonModels.JsonGenreList
import com.dreamisi.moviebase.data.JsonModels.JsonMovieDetails
import com.dreamisi.moviebase.data.JsonModels.JsonPopularMovies
import retrofit2.http.GET
import retrofit2.http.Path


interface TheMovieDataBaseAPI {

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopularMoviesList(): JsonPopularMovies

    @GET("movie/{id}?language=en-US")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): JsonMovieDetails

    @GET("genre/movie/list?language=en")
    suspend fun getGenreList(): JsonGenreList
}
