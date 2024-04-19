package com.dreamisi.moviebase

import android.app.Application
import com.dreamisi.moviebase.data.*

class MovieBase : Application(), RepositoryProvider {

    private var repository: Repository? = null

    override fun onCreate() {
        super.onCreate()
        repository = Repository()

    }

    override fun provideRepository(): Repository =
        repository ?: error("empty Repository")
}