package com.example.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.data.model.ArticleDto


@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String?,
    val content: String?,
    val author: String?,
    val publishedAt: String?,
    val url: String?,
    val urlToImage: String?,
    val summary: String?,
    val imageUrl: String?,
    val newsSite: String?,
    val published_at: String?,
    val news_site: String?,
    val image_url: String?
)

fun ArticleDto.toEntity(): ArticleEntity {
    return ArticleEntity(
        id = id,
        title = title,
        description = description,
        content = content,
        author = author,
        publishedAt = publishedAt,
        url = url,
        urlToImage = urlToImage,
        summary = summary,
        imageUrl = imageUrl,
        newsSite = newsSite,
        published_at = publishedAt,
        news_site = newsSite,
        image_url = imageUrl
    )
}









