package com.dreamisi.moviebase.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamisi.moviebase.data.Repository
import com.dreamisi.moviebase.data.models.Movie
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val repository: Repository
    ) : ViewModel() {
    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData : LiveData<List<Movie>> = _moviesLiveData

    init {
        updateData()
    }

    private fun updateData() {
        viewModelScope.launch {
            _moviesLiveData.value = repository.getMoviesList()
        }
    }
}