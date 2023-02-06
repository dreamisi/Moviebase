package com.dreamisi.moviebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


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
        val fragment = when (movieId) {
            1 -> MoviesDetailsFragment()
            else -> null
        }
        fragment?.apply {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, this)
                .commit()
        }
    }


}