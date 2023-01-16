package domain

import com.dreamisi.moviebase.R
import data.models.Movie

class MoviesDataSource() {

    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                id = "1",
                name = "Avengers: End Game",
                duration = "137",
                reviews = "125",
                genre = "Action, Adventure, Drama",
                rating = 4,
                favorite = false,
                pg = R.drawable.pg13,
                image = R.drawable.movie
            ),
            Movie(
                id = "2",
                name = "Tenet",
                duration = "97",
                reviews = "98",
                genre = "Action, Sci-Fi, Thriller ",
                rating = 5,
                favorite = true,
                pg = R.drawable.pg16,
                image = R.drawable.tenet
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
