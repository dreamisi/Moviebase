package com.dreamisi.moviebase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {
    private var fragmentMoviesListListener: FragmentMoviesListListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        view?.findViewById<ImageView>(R.id.movie_image)?.apply {
            setOnClickListener {
                fragmentMoviesListListener?.onFilmCardClicked()
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentMoviesListListener) {
            fragmentMoviesListListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentMoviesListListener = null
    }

    interface FragmentMoviesListListener {
        fun onFilmCardClicked()
    }

}