package com.codercampy.marvelapp.api

import com.codercampy.marvelapp.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("v1/public/characters")
    fun getCharacters(
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 100,
        @Query("orderBy") orderBy: String = "-modified",
    ): Call<BaseResponse>

    @GET("v1/public/characters/{id}")
    fun getCharacter(
        @Path("id") id: Int,
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Call<BaseResponse>

}