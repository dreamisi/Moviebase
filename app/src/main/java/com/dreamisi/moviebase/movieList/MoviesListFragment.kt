package com.dreamisi.moviebase.movieList


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamisi.moviebase.MainActivity
import com.dreamisi.moviebase.R
import com.dreamisi.moviebase.data.MovieRepositoryProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private var recycler: RecyclerView? = null
    private val scope = CoroutineScope(Dispatchers.IO)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.movies_cards)
        recycler?.adapter = MoviesListAdapter(
            requireContext(),
            onClick = { movieId: Int -> onFilmCardClicked(movieId) })
        recycler?.layoutManager = GridLayoutManager(requireContext(), 2)
        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? MoviesListAdapter)?.apply {

            val application = requireActivity().application as MovieRepositoryProvider
            val repository = application.provideMovieRepository()

            scope.launch {
                val movies = repository.loadMovies()
                submitList(movies)
            }
        }
    }

    private fun onFilmCardClicked(movieId: Int) {
        (requireActivity() as? MainActivity)?.onFilmCardClicked(movieId)
    }

}