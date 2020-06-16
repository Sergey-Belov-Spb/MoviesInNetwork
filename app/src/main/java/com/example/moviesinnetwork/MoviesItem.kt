package com.example.moviesinnetwork

data class MoviesItem (
    val id: Int,
    val name: String,
    val image: String,
    var inFavorite: Boolean
)