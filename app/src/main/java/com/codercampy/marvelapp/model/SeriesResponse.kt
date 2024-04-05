package com.codercampy.marvelapp.model

data class SeriesResponse (

    val data: DataSeriesResponse
)

data class DataSeriesResponse(

    val results: List<ItemModel>
)

