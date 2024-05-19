package com.dreamisi.moviebase.data.jsonmodels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonGenreList(
    @SerialName("genres")
    val genres: List<Genre>
) {
    @Serializable
    data class Genre(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String
    )
}