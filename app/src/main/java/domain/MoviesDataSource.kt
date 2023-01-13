package domain

import com.dreamisi.moviebase.R
import data.models.Movie

class MoviesDataSource() {

    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                "1",
                "Avengers: End Game",
                "137",
                "125",
                "Action, Adventure, Drama",
                4,
                false,
                R.drawable.pg13,
                R.drawable.movie
            ),
            Movie(
                "2",
                "Tenet",
                "97",
                "98",
                "Action, Sci-Fi, Thriller ",
                5,
                true,
                R.drawable.pg16,
                R.drawable.tenet
            ),
            Movie(
                "3",
                "Black Widow",
                "102",
                "38",
                "Action, Adventure, Sci-Fi",
                4,
                false,
                R.drawable.pg13,
                R.drawable.black_widow
            ),
            Movie(
                "4",
                "Wonder Woman 1984",
                "120",
                "74",
                "Action, Adventure, Fantasy",
                5,
                false,
                R.drawable.pg13,
                R.drawable.wonder_woman_1984
            )

        )

    }

}
