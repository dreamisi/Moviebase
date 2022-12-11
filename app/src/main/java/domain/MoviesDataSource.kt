package domain

import com.dreamisi.moviebase.R
import data.models.Movie

class MoviesDataSource(){

    fun getMovies(): List<Movie>{
        return listOf(
            Movie("Avengers: End Game", "137 MIN","125",
                "Action, Adventure, Drama", 4,false,R.drawable.pg13, R.drawable.movie)
        )

    }

}
