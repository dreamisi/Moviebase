package com.dreamisi.moviebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.academy.fundamentals.homework.data.JsonMovieRepository
import com.android.academy.fundamentals.homework.data.MovieRepository
import com.dreamisi.moviebase.data.MovieRepositoryProvider


class MainActivity : AppCompatActivity(), MovieRepositoryProvider {
    private val jsonMovieRepository = JsonMovieRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, MoviesListFragment())
                .commit()
        }

    }

    fun onFilmCardClicked(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MoviesDetailsFragment.create(movieId)).addToBackStack(null)
            .commit()
    }

    override fun provideMovieRepository(): MovieRepository = jsonMovieRepository


}