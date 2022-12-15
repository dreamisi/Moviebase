package com.dreamisi.moviebase

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import domain.MoviesDataSource

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private var recycler: RecyclerView? = null

    private var fragmentMoviesListListener: FragmentMoviesListListener? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.movies_cards)
        recycler?.adapter = MoviesListAdapter()
        recycler?.layoutManager = GridLayoutManager(requireContext(),2)
        view.findViewById<ImageView>(R.id.movie_image)?.apply {//нужно переписать этот код, кликлистенер ссылается на нулевой объект теперь
            setOnClickListener {
                fragmentMoviesListListener?.onFilmCardClicked()
            }
        }
    }


    override fun onStart() {
        super.onStart()
        updateData()
        (context as? FragmentMoviesListListener).let { fragmentMoviesListListener = it }

    }

    private fun updateData(){
        (recycler?.adapter as? MoviesListAdapter)?.apply {
            bindMovies(MoviesDataSource().getMovies())
        }
    }

    override fun onPause() {
        super.onPause()
        fragmentMoviesListListener = null
    }

    interface FragmentMoviesListListener {
        fun onFilmCardClicked()
    }
    companion object{
        fun newInstance() = FragmentMoviesList()
    }


}