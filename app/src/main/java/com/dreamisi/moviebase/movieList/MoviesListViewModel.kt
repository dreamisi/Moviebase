package com.dreamisi.moviebase.movieList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dreamisi.moviebase.data.MovieRepositoryProvider
import com.dreamisi.moviebase.data.models.Movie
import kotlinx.coroutines.launch

class MoviesListViewModel(application: Application) : AndroidViewModel(application) {

    private val _mutableRepository = MutableLiveData<List<Movie>>()
    val repository = _mutableRepository

    fun updateData() {
        viewModelScope.launch {
            val application = getApplication<Application>() as MovieRepositoryProvider
            val repository = application.provideMovieRepository()
            _mutableRepository.value = repository.loadMovies()
        }
    }
}