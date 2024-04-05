package com.codercampy.marvelapp.model

import com.google.gson.annotations.SerializedName

data class ItemModel(
    val id: Int,
    @SerializedName("name", alternate = arrayOf("title"))
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)