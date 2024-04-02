package com.codercampy.marvelapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com:443/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val marvelApi = retrofit.create(MarvelApi::class.java)

}