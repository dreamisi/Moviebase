package com.dreamisi.moviebase

import android.app.Application
import com.dreamisi.moviebase.data.JsonMovieRepository
import com.dreamisi.moviebase.data.MovieRepository
import com.dreamisi.moviebase.data.MovieRepositoryProvider

class MovieBase : Application(), MovieRepositoryProvider {

    private var movieRepository: JsonMovieRepository? = null

    override fun onCreate() {
        super.onCreate()
        movieRepository = JsonMovieRepository(this)

    }

    override fun provideMovieRepository(): MovieRepository =
        movieRepository ?: error("empty Movie Repository")
}