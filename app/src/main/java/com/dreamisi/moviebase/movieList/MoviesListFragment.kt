package com.dreamisi.moviebase.movieList


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamisi.moviebase.MainActivity
import com.dreamisi.moviebase.R
import com.dreamisi.moviebase.data.models.Movie


class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private var recycler: RecyclerView? = null
    private val viewModel: MoviesListViewModel by viewModels { MoviesListViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.movies_cards)
        recycler?.adapter = MoviesListAdapter(
            requireContext(),
            onClick = { movieId: Int -> onFilmCardClicked(movieId) })
        recycler?.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.moviesLiveData.observe(this.viewLifecycleOwner, this::updateData)
    }

    private fun updateData(movies: List<Movie>) {
        (recycler?.adapter as? MoviesListAdapter)?.submitList(movies)
    }


    private fun onFilmCardClicked(movieId: Int) {
        (requireActivity() as? MainActivity)?.onFilmCardClicked(movieId)
    }
}