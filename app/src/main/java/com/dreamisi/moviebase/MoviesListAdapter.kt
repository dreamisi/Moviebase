package com.dreamisi.moviebase

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import data.models.Movie

class MoviesListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var movies = listOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoviesListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MoviesListViewHolder -> {
                holder.onBind(movies[position])
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
    }

    private class MoviesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.movie_image)
        private val name: TextView = itemView.findViewById(R.id.film_name_movies_list)
        private val duration: TextView = itemView.findViewById(R.id.duration)

        //private val favorite: ImageView = itemView.findViewById(R.id.favorite)
        private val genre: TextView = itemView.findViewById(R.id.film_genre)
        private val reviews: TextView = itemView.findViewById(R.id.reviews_movies_list)
        private val pg: ImageView = itemView.findViewById(R.id.pg)
        //private val rating_1_st: ImageView = itemView.findViewById(R.id.first_star)
        //private val rating_2_st: ImageView = itemView.findViewById(R.id.second_star)
        //private val rating_3_st: ImageView = itemView.findViewById(R.id.third_star)
        //private val rating_4_st: ImageView = itemView.findViewById(R.id.fourth_star)
        //private val rating_5_st: ImageView = itemView.findViewById(R.id.fifth_star)

        fun onBind(movie: Movie) {
            image.setImageResource(movie.image)
            name.text = movie.name
            duration.text = movie.duration + " MIN"
            //favorite.visibility =
            genre.text = movie.genre
            reviews.text = movie.reviews + " reviews"
            pg.setImageResource(movie.pg)


        }

    }
}