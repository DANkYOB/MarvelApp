package com.codercampy.marvelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    val path: String,
    val extension: String
) : Parcelable {

    fun getUrl(): String {
        return "$path.$extension".replace("http://", "https://")
    }

}