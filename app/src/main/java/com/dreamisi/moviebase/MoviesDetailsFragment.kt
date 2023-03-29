package com.dreamisi.moviebase

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dreamisi.moviebase.data.MovieRepositoryProvider
import com.dreamisi.moviebase.data.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesDetailsFragment : Fragment(R.layout.fragment_movies_details) {

    private var recycler: RecyclerView? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieID = arguments?.getSerializable(MOVIE_ID) as Int? ?: return
        recycler = view.findViewById(R.id.actor_cards)
        recycler?.adapter = ActorsListAdapter(requireContext())
        recycler?.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
        scope.launch {
            val repository =
                (requireActivity() as MovieRepositoryProvider).provideMovieRepository()
            val movie = repository.loadMovie(movieID)
            if (movie != null)
                updateData(movie)


        }


    }

    private fun updateData(movie: Movie) {
        val cover = view?.findViewById<ImageView>(R.id.movie_cover) ?: return
        Glide.with(this).load(movie.detailImageUrl).into(cover)
        view?.findViewById<TextView>(R.id.age_limit)?.text =
            context?.getString(R.string.pg, movie.pgAge)
        view?.findViewById<TextView>(R.id.film_name)?.text = movie.title
        view?.findViewById<TextView>(R.id.film_genre)?.text = movie.genres.joinToString { it.name }
        view?.findViewById<TextView>(R.id.reviews)?.text =
            context?.getString(R.string.reviews, movie.reviewCount)
        view?.findViewById<TextView>(R.id.storyline_content)?.text = movie.storyLine
        listOf(
            view?.findViewById(R.id.first_star),
            view?.findViewById(R.id.second_star),
            view?.findViewById(R.id.third_star),
            view?.findViewById(R.id.fourth_star),
            view?.findViewById<ImageView>(R.id.fifth_star)
        ).forEachIndexed { index, imageView ->
            val startIndex = index + 1
            val startRes = if (startIndex <= movie.rating) {
                R.drawable.red_star_movies_list
            } else {
                R.drawable.gray_star_movies_list
            }
            imageView?.setImageResource(startRes)
        }


    }

    companion object {
        private const val MOVIE_ID = "movie_id"

        fun create(movieId: Int) = MoviesDetailsFragment().also {
            val arg = bundleOf(
                MOVIE_ID to movieId
            )
            it.arguments = arg
        }
    }
}