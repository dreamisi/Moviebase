package com.dreamisi.moviebase.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamisi.moviebase.data.MovieRepository
import com.dreamisi.moviebase.data.models.Movie
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val myRepository: MovieRepository,
    private val movieID: Int,
) : ViewModel() {

    private val _movieDetailsLiveData = MutableLiveData<Movie>()
    val movieDetailsLiveData: LiveData<Movie> = _movieDetailsLiveData

    init {
        updateData()
    }

    private fun updateData() {
        viewModelScope.launch {
            _movieDetailsLiveData.value = myRepository.loadMovie(movieId = movieID)
        }
    }
}