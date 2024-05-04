package com.dreamisi.moviebase.data

import com.dreamisi.moviebase.data.JsonModels.JsonGenreList
import com.dreamisi.moviebase.data.JsonModels.JsonMovieDetails
import com.dreamisi.moviebase.data.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.dreamisi.moviebase.data.JsonModels.JsonPopularMovies
import com.dreamisi.moviebase.data.models.Genre
import com.dreamisi.moviebase.data.models.MovieDetails
import com.dreamisi.moviebase.data.services.TheMovieDataBaseAPI

class Repository(private val api: TheMovieDataBaseAPI) {

    private var cachedMoviesList: List<Movie>? = null
    private var cachedGenreList: List<Genre>? = null
    private var cachedMovieDetails: MovieDetails? = null


    suspend fun getMoviesList(): List<Movie> = withContext(Dispatchers.IO) {
        val cachedGenre = cachedGenreList ?: loadGenres()
        cachedGenreList = cachedGenre
        val cachedMovies = cachedMoviesList ?: loadMoviesFromDB(cachedGenreList ?: emptyList())
        cachedMoviesList = cachedMovies
        cachedMovies
    }

    suspend fun getMovieDetails(id: Int) = withContext(Dispatchers.IO) {
        val cachedMovie = cachedMovieDetails
        if (cachedMovie != null && cachedMovie.id == id) {
            cachedMovie
        } else {
            val movieDetailsFromDB = loadMovieDetails(id = id)
            cachedMovieDetails = movieDetailsFromDB
            movieDetailsFromDB
        }
    }

    private suspend fun loadGenres(): List<Genre> = parseGenre(api.getGenreList())

    private fun parseGenre(jsonGenreList: JsonGenreList): List<Genre> {
        return jsonGenreList.genres.map { genre ->
            Genre(
                id = genre.id,
                name = genre.name
            )
        }
    }

    private suspend fun loadMoviesFromDB(genreList: List<Genre>): List<Movie> =
        parseMovie(api.getPopularMoviesList(), genreList)

    private fun parseMovie(
        jsonPopularMovies: JsonPopularMovies,
        genreList: List<Genre>
    ): List<Movie> {
        return jsonPopularMovies.results.map { jsonMovie ->
            Movie(
                id = jsonMovie.id,
                title = jsonMovie.title,
                storyLine = jsonMovie.overview,
                imageUrl = imageUrlHead + jsonMovie.posterPath,
                genres = jsonMovie.genreIds.map { id ->
                    Genre(
                        id = id,
                        name = genreList.find { it.id == id }?.name ?: error("Wrong genre ID")
                    )
                },
                isLiked = false,
                pgAge = if (jsonMovie.adult) 16 else 13,
                rating = (jsonMovie.voteAverage / 2).toInt(),
                reviewCount = jsonMovie.voteCount
            )
        }
    }


    private suspend fun loadMovieDetails(id: Int): MovieDetails =
        parseMovieDetails(api.getMovieDetails(id = id))

    private fun parseMovieDetails(jsonMovieDetails: JsonMovieDetails): MovieDetails {
        return MovieDetails(
            id = jsonMovieDetails.id,
            actors = emptyList(),
            detailImageUrl = imageUrlHead + jsonMovieDetails.posterPath,
            genres = jsonMovieDetails.genres.map { jsonGenre ->
                Genre(
                    id = jsonGenre.id,
                    name = jsonGenre.name
                )
            },
            imageUrl = jsonMovieDetails.backdropPath,
            isLiked = false,
            pgAge = if (jsonMovieDetails.adult) 16 else 0,
            rating = (jsonMovieDetails.voteAverage / 2).toInt(),
            reviewCount = jsonMovieDetails.voteCount,
            title = jsonMovieDetails.title,
            storyLine = jsonMovieDetails.overview,
            runningTime = jsonMovieDetails.runtime
        )
    }

    companion object {
        private const val imageUrlHead = "https://image.tmdb.org/t/p/w500/"
    }

}