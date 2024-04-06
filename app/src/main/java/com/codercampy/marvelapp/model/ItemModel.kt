package com.codercampy.marvelapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemModel(
    val id: Int,
    @SerializedName("name", alternate = arrayOf("title"))
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
): Parcelable