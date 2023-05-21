package com.dreamisi.moviebase.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamisi.moviebase.data.MovieRepository
import com.dreamisi.moviebase.data.models.Movie
import kotlinx.coroutines.launch

class MoviesListViewModel(

    private val myRepository: MovieRepository,

    ) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val repository: LiveData<List<Movie>> = _moviesLiveData

    init {
        updateData()
    }

    private fun updateData() {
        viewModelScope.launch {
            val repository = myRepository
            _moviesLiveData.value = repository.loadMovies()
        }
    }
}