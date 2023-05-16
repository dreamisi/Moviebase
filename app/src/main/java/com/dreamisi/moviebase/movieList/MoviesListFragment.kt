package com.dreamisi.moviebase.movieList


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamisi.moviebase.MainActivity
import com.dreamisi.moviebase.R
import com.dreamisi.moviebase.data.models.Movie


class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private var recycler: RecyclerView? = null
    private lateinit var viewModel: MoviesListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = MoviesListViewModel(requireActivity().application)

        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.movies_cards)
        recycler?.adapter = MoviesListAdapter(
            requireContext(),
            onClick = { movieId: Int -> onFilmCardClicked(movieId) })
        recycler?.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.repository.observe(this.viewLifecycleOwner, this::updateData)
        viewModel.updateData()
    }

    private fun updateData(movies: List<Movie>) {
        (recycler?.adapter as? MoviesListAdapter)?.apply {
            submitList(movies)
        }
    }


    private fun onFilmCardClicked(movieId: Int) {
        (requireActivity() as? MainActivity)?.onFilmCardClicked(movieId)
    }

}