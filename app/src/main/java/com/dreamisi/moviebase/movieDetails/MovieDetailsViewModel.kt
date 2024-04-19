package com.dreamisi.moviebase.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamisi.moviebase.data.Repository
import com.dreamisi.moviebase.data.models.MovieDetails
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: Repository,
    private val movieID: Int,
) : ViewModel() {

    private val _movieDetailsDetailsLiveData = MutableLiveData<MovieDetails>()
    val movieDetailsDetailsLiveData: LiveData<MovieDetails> = _movieDetailsDetailsLiveData

    init {
        updateData()
    }

    private fun updateData() {
        viewModelScope.launch {
            _movieDetailsDetailsLiveData.value = repository.getMovieDetails(movieID)
        }
    }
}