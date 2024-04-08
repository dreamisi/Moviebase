package com.dreamisi.moviebase.data

import com.dreamisi.moviebase.data.services.TheMovieDataBaseAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create


object NetworkModule {

    private const val baseURL = "https://api.themoviedb.org/"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(loggingInterceptor)
        .build()
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }
    private val contentType = "application/json".toMediaType()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(httpClient)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    val theMovieDataBaseAPI: TheMovieDataBaseAPI = retrofit.create()

}