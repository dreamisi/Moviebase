package com.dreamisi.moviebase.data

interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}