package com.example.newsapp.data.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SpaceNewsApiProvider {

    private const val BASE_URL = "https://api.spaceflightnewsapi.net/v4/"

    val api: SpaceNewsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpaceNewsApi::class.java)
    }
}
