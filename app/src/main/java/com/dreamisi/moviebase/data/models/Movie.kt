package com.dreamisi.moviebase.data.models

data class Movie(
    val id: Int,
    val title: String,
    val storyLine: String,
    val imageUrl: String,
    val rating: Int,
    val reviewCount: Int,
    val pgAge: Int,
    val genres: List<Genre>,
    val isLiked: Boolean
)