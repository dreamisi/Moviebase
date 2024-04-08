package com.dreamisi.moviebase.movieList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dreamisi.moviebase.R
import com.dreamisi.moviebase.data.MovieResponse
import com.dreamisi.moviebase.data.NetworkModule

class MoviesListAdapter(
    context: Context,
    private val onClick: (movieId: Int) -> Unit
) :
    ListAdapter<MovieResponse, MoviesListAdapter.MoviesListViewHolder>(MoviesListCallBack()) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        return MoviesListViewHolder(
            itemView = inflater.inflate(R.layout.view_holder_movie, parent, false),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class MoviesListViewHolder(
        itemView: View,
        private val onClick: (movieId: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.movie_image)
        private val name: TextView = itemView.findViewById(R.id.film_name_movies_list)
        private val duration: TextView = itemView.findViewById(R.id.duration)
        private val favorite: ImageView = itemView.findViewById(R.id.favorite)
        private val genre: TextView = itemView.findViewById(R.id.film_genre)
        private val reviews: TextView = itemView.findViewById(R.id.reviews_movies_list)
        private val pgText: TextView = itemView.findViewById(R.id.pg_text)
        private val rating1St: ImageView = itemView.findViewById(R.id.first_star_movies_list)
        private val rating2St: ImageView = itemView.findViewById(R.id.second_star_movies_list)
        private val rating3St: ImageView = itemView.findViewById(R.id.third_star_movies_list)
        private val rating4St: ImageView = itemView.findViewById(R.id.fourth_star_movies_list)
        private val rating5St: ImageView = itemView.findViewById(R.id.fifth_star_movies_list)

        fun onBind(movieResponse: MovieResponse) {
            itemView.setOnClickListener { onClick.invoke(movieResponse.id) }
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500/" + movieResponse.posterPath).into(image)
            name.text = movieResponse.title
            duration.isVisible = false // temporarily non-use date for test
            // duration.text = itemView.context.getString(R.string.min, movie.runningTime)
            when (movieResponse.isLiked) {
                false -> favorite.setImageResource(R.drawable.like_not_active)
                true -> favorite.setImageResource(R.drawable.like_active)
            }
            //genre.text = movieResponse.genres.joinToString { it.name }
            reviews.text = itemView.context.getString(R.string.reviews, movieResponse.voteCount)
            pgText.text =
                itemView.context.getString(R.string.pg, if (movieResponse.adult) 16 else 0)
            listOf(
                rating1St,
                rating2St,
                rating3St,
                rating4St,
                rating5St
            ).forEachIndexed { index, imageView ->
                val startIndex = index + 1
                val startRes = if (startIndex <= movieResponse.voteAverage) {
                    R.drawable.red_star_movies_list
                } else {
                    R.drawable.gray_star_movies_list
                }
                imageView.setImageResource(startRes)
            }


        }

    }

    class MoviesListCallBack : DiffUtil.ItemCallback<MovieResponse>() {
        override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem == newItem
        }

    }

}