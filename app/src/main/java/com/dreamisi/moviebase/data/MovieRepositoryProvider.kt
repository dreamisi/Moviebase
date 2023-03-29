package com.dreamisi.moviebase.data

import com.android.academy.fundamentals.homework.data.MovieRepository

interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}