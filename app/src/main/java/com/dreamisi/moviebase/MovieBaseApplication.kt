package com.dreamisi.moviebase

import android.app.Application
import com.dreamisi.moviebase.data.*
import com.dreamisi.moviebase.data.services.TheMovieDataBaseAPI

class MovieBaseApplication : Application() {

    private var repository: Repository? = null
    private val api: TheMovieDataBaseAPI = NetworkModule().theMovieDataBaseAPI

    override fun onCreate() {
        super.onCreate()
        repository = Repository(api)

    }

    fun provideRepository(): Repository =
        repository ?: error("empty Repository")
}