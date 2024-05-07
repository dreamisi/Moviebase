package com.dreamisi.moviebase.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.dreamisi.moviebase.MovieBaseApplication

class MovieDetailsViewModelFactory(private val movieID: Int) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val application =
            checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

        return MovieDetailsViewModel(
            (application as MovieBaseApplication).provideRepository(),
            movieID
        ) as T

    }
}