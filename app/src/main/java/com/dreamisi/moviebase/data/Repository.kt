package com.dreamisi.moviebase.data

import com.dreamisi.moviebase.data.JsonModels.JsonGenreList
import com.dreamisi.moviebase.data.JsonModels.JsonMovieDetails
import com.dreamisi.moviebase.data.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.dreamisi.moviebase.data.JsonModels.JsonPopularMoviesResponse
import com.dreamisi.moviebase.data.models.Genre
import com.dreamisi.moviebase.data.models.MovieDetails

class Repository {

    private var moviesList: List<Movie>? = null
    private var genreList: List<Genre>? = null
    private val networkModule = NetworkModule
    private var movieDetails: MovieDetails? = null


    suspend fun getMoviesList(): List<Movie> = withContext(Dispatchers.IO) {
        val cachedGenre = genreList
        if (cachedGenre == null) {
            val genreFromDB = loadGenresFromDB()
            genreList = genreFromDB
        }
        val cachedMovies = moviesList
        if (cachedMovies != null) {
            cachedMovies
        } else {
            val moviesFromDB = loadMoviesFromDB(genreList ?: emptyList())
            moviesList = moviesFromDB
            moviesFromDB
        }
    }

    private suspend fun loadGenresFromDB(): List<Genre> {
        val data = networkModule.theMovieDataBaseAPI.getGenreList()
        return parseGenre(data)
    }

    private fun parseGenre(jsonGenreList: JsonGenreList): List<Genre> {
        return jsonGenreList.genres.map { genre ->
            Genre(
                id = genre.id,
                name = genre.name
            )
        }
    }

    private suspend fun loadMoviesFromDB(genreList: List<Genre>): List<Movie> {
        val data = networkModule.theMovieDataBaseAPI.getPopularMoviesList()
        return parseMovie(data, genreList)
    }

    private fun parseMovie(
        jsonPopularMoviesResponse: JsonPopularMoviesResponse,
        genreList: List<Genre>
    ): List<Movie> {
        return jsonPopularMoviesResponse.results.map { jsonMovie ->
            Movie(
                id = jsonMovie.id,
                title = jsonMovie.title,
                storyLine = jsonMovie.overview,
                imageUrl = jsonMovie.posterPath,
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

    suspend fun getMovieDetails(id: Int) = withContext(Dispatchers.IO) {
        val cachedMovie = movieDetails
        if (cachedMovie != null && cachedMovie.id == id) {
            cachedMovie
        } else {
            val movieDetailsFromDB = loadMovieDetails(id = id)
            movieDetails = movieDetailsFromDB
            movieDetailsFromDB
        }
    }

    private suspend fun loadMovieDetails(id: Int): MovieDetails {
        val data = networkModule.theMovieDataBaseAPI.getMovieDetails(id = id)
        return parseMovieDetails(data)
    }

    private fun parseMovieDetails(jsonMovieDetails: JsonMovieDetails): MovieDetails {
        return MovieDetails(
            id = jsonMovieDetails.id,
            actors = emptyList(),
            detailImageUrl = jsonMovieDetails.posterPath,
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

}