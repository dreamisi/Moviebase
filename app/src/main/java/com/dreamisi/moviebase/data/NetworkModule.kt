package com.dreamisi.moviebase.data

import com.dreamisi.moviebase.BuildConfig
import com.dreamisi.moviebase.data.services.TheMovieDataBaseAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit


class NetworkModule {
    private val retrofit = createRetrofit()

    val theMovieDataBaseAPI: TheMovieDataBaseAPI = retrofit.create()

    private fun createRetrofit(): Retrofit {
        val baseURL = "https://api.themoviedb.org/3/"
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer ${BuildConfig.API_KEY}")
                    .method(original.method, original.body)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addNetworkInterceptor(loggingInterceptor)
            .build()
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }


}