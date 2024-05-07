package com.dreamisi.moviebase.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.dreamisi.moviebase.MovieBaseApplication

class MoviesListViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val application =
            checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

        return MoviesListViewModel(
            (application as MovieBaseApplication).provideRepository()
        ) as T

    }
}