package com.codercampy.marvelapp.model

data class BaseResponse(
    val data: DataResponse
)

data class DataResponse(
    val total: Int,
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Character>
)