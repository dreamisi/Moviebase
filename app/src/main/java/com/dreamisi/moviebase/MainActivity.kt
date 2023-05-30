package com.dreamisi.moviebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamisi.moviebase.movieDetails.MoviesDetailsFragment
import com.dreamisi.moviebase.movieList.MoviesListFragment


class MainActivity : AppCompatActivity() {

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
            .replace(R.id.main_container, MoviesDetailsFragment.create(movieId))
            .addToBackStack(null)
            .commit()
    }


}