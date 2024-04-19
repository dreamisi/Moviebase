package com.dreamisi.moviebase.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.dreamisi.moviebase.MovieBase

class MoviesListViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val application =
            checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

        return MoviesListViewModel(
            (application as MovieBase).provideRepository()
        ) as T

    }
}