package com.dreamisi.moviebase.data

interface RepositoryProvider {
    fun provideRepository(): Repository
}