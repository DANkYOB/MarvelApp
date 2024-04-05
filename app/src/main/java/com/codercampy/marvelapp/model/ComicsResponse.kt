package com.codercampy.marvelapp.model

data class ComicsResponse (

    val data: DataComicsResponse
)

data class DataComicsResponse(

    val results: List<ItemModel>
)
