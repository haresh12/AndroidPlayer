package com.example.androidplayer

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface JsonPlaceHolderApi {
    @GET("search")
    fun getGifList(
        @Query("api_key") apiKey: String,
        @Query("q") searchQuery: String,
        @Query("limit") limit: Int
    ): Call<GifModel>
    companion object Factory {
        fun create(): JsonPlaceHolderApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.giphy.com/v1/gifs/")
                .build()
            return retrofit.create(JsonPlaceHolderApi::class.java);
        }
    }
}