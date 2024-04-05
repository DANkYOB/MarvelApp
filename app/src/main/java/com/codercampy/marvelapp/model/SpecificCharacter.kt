package com.codercampy.marvelapp.model

data class SpecificCharacter (
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail,
    val comics : String,
    val series : String,
    val stories : String,
    val events : String
)