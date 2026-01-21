package com.example.newsapp.data.model

import com.google.gson.annotations.SerializedName

// DTO from API
data class ArticleDto(
    val id: Int,
    val title: String,
    val description: String?,
    val content: String?,
    val author: String?,
    @SerializedName("published_at")
    val publishedAt: String?,
    val url: String?,
    val urlToImage: String?,
    val summary: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("news_site")
    val newsSite: String?
) {

}


