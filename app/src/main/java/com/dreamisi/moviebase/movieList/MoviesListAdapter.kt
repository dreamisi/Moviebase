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
import com.dreamisi.moviebase.data.models.Movie

class MoviesListAdapter(
    context: Context,
    private val onClick: (movieId: Int) -> Unit
) :
    ListAdapter<Movie, MoviesListAdapter.MoviesListViewHolder>(MoviesListCallBack()) {

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

        fun onBind(movie: Movie) {
            itemView.setOnClickListener { onClick.invoke(movie.id) }
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500/" + movie.imageUrl).into(image)
            name.text = movie.title
            duration.isVisible = false // temporarily non-use date for test
            // duration.text = itemView.context.getString(R.string.min, movie.runningTime)
            when (movie.isLiked) {
                false -> favorite.setImageResource(R.drawable.like_not_active)
                true -> favorite.setImageResource(R.drawable.like_active)
            }
            genre.text = movie.genres.joinToString { it.name }
            reviews.text = itemView.context.getString(R.string.reviews, movie.reviewCount)
            pgText.text =
                itemView.context.getString(R.string.pg, movie.pgAge)
            listOf(
                rating1St,
                rating2St,
                rating3St,
                rating4St,
                rating5St
            ).forEachIndexed { index, imageView ->
                val startIndex = index + 1
                val startRes = if (startIndex <= movie.rating) {
                    R.drawable.red_star_movies_list
                } else {
                    R.drawable.gray_star_movies_list
                }
                imageView.setImageResource(startRes)
            }


        }

    }

    class MoviesListCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

}