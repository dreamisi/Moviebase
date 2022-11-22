package com.dreamisi.moviebase

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private var fragmentMoviesListListener: FragmentMoviesListListener? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.movie_image)?.apply {
            setOnClickListener {
                fragmentMoviesListListener?.onFilmCardClicked()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentMoviesListListener) {
            fragmentMoviesListListener = context
        }
    }

    override fun onPause() {
        super.onPause()
        fragmentMoviesListListener = null
    }

    interface FragmentMoviesListListener {
        fun onFilmCardClicked()
    }

}