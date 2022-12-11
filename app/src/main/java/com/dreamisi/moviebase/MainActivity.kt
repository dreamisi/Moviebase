package com.dreamisi.moviebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), FragmentMoviesList.FragmentMoviesListListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMoviesList.newInstance())
                .commit()
        }

    }

    override fun onFilmCardClicked() {
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.main_container, FragmentMoviesDetails()).commit()
    }
}