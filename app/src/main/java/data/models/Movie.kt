package data.models

data class Movie(
    val id: String,
    val name: String,
    val duration: String,
    val reviews: String,
    val genre: String,
    val rating: Int,
    val favorite: Boolean,
    val pg: Int,
    val image: Int
)
