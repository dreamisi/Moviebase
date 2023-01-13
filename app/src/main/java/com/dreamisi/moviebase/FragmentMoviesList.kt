package com.dreamisi.moviebase


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import domain.MoviesDataSource


class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private var recycler: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.movies_cards)
        recycler?.adapter = MoviesListAdapter(
            onClick = { movieId -> onFilmCardClicked(movieId) }
        )
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

    private fun onFilmCardClicked(movieId: String) {
        (requireActivity() as? FragmentMoviesListListener)?.onFilmCardClicked(movieId)
    }

    interface FragmentMoviesListListener {
        fun onFilmCardClicked(movieId: String)
    }

}
