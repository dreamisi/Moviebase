package com.dreamisi.moviebase.data.models

data class Movie(
    val id: Int,
    val name: String,
    val duration: Int,
    val reviews: String,
    val genre: String,
    val rating: Int,
    val favorite: Boolean,
    val pg: Int,
    val image: Int
)
