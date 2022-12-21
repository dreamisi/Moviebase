package com.dreamisi.moviebase


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import data.models.Movie
import domain.MoviesDataSource


class FragmentMoviesList : Fragment(R.layout.fragment_movies_list),
    MoviesListAdapter.OnItemClickListener {

    private var recycler: RecyclerView? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.movies_cards)
        recycler?.adapter = MoviesListAdapter(this)
        recycler?.layoutManager = GridLayoutManager(requireContext(), 2)
        }



    override fun onStart() {
        super.onStart()
        updateData()

    }

    private fun updateData() {
        (recycler?.adapter as? MoviesListAdapter)?.apply {
            bindMovies(MoviesDataSource().getMovies())
        }
    }


    companion object {
        fun newInstance() = FragmentMoviesList()
    }

    override fun onItemClicked(movie: Movie) {
        Toast.makeText(
            context,
            "You pressed on movie card, but functional it's empty",
            Toast.LENGTH_SHORT
        ).show()
    }


}