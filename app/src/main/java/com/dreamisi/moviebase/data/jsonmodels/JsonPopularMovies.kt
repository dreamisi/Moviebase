package com.dreamisi.moviebase.data.jsonmodels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonPopularMovies(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<JsonMoviesListCard>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)