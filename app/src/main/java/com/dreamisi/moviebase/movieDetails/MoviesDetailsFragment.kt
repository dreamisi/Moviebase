package com.dreamisi.moviebase.movieDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dreamisi.moviebase.R
import com.dreamisi.moviebase.data.models.MovieDetails

class MoviesDetailsFragment : Fragment(R.layout.fragment_movies_details) {

    private var recycler: RecyclerView? = null
    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(
            arguments?.getInt(MOVIE_ID) ?: error("Empty ID of MovieDetails")
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.actor_cards)
        recycler?.adapter = ActorsListAdapter(requireContext())
        recycler?.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
        viewModel.movieDetailsDetailsLiveData.observe(
            this.viewLifecycleOwner,
            { updateData(it, view) })
    }

    private fun updateData(movieDetails: MovieDetails, view: View) {
        val cover = view.findViewById<ImageView>(R.id.movie_cover)
        if (cover != null) {
            Glide.with(this).load(movieDetails.detailImageUrl).into(cover)
        }
        view.findViewById<TextView>(R.id.age_limit)?.text =
            context?.getString(R.string.pg, movieDetails.pgAge)
        view.findViewById<TextView>(R.id.film_name)?.text = movieDetails.title
        view.findViewById<TextView>(R.id.film_genre)?.text =
            movieDetails.genres.joinToString { it.name }
        view.findViewById<TextView>(R.id.reviews)?.text =
            context?.getString(R.string.reviews, movieDetails.reviewCount)
        view.findViewById<TextView>(R.id.storyline_content)?.text = movieDetails.storyLine
        listOf(
            view.findViewById(R.id.first_star),
            view.findViewById(R.id.second_star),
            view.findViewById(R.id.third_star),
            view.findViewById(R.id.fourth_star),
            view.findViewById<ImageView>(R.id.fifth_star)
        ).forEachIndexed { index, imageView ->
            val startIndex = index + 1
            val startRes = if (startIndex <= movieDetails.rating) {
                R.drawable.red_star_movies_list
            } else {
                R.drawable.gray_star_movies_list
            }
            imageView?.setImageResource(startRes)
        }

        val adapter =
            view.findViewById<RecyclerView>(R.id.actor_cards)?.adapter as ActorsListAdapter
        adapter.submitList(movieDetails.actors)
        Log.d(TAG, "ADAPTER BINDING.......................")

    }

    companion object {
        private const val MOVIE_ID = "movie_id"
        private const val TAG = "movieDetails"

        fun create(movieId: Int) = MoviesDetailsFragment().also {
            val arg = bundleOf(
                MOVIE_ID to movieId
            )
            it.arguments = arg
        }
    }
}