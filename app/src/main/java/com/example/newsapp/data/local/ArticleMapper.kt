package com.example.newsapp.data.model

import com.example.newsapp.data.local.ArticleEntity

// DTO → Domain Model (for API fetch)
fun ArticleDto.toArticle(): Article {
    return Article(
        id = this.id,
        title = this.title,
        description = this.description,
        content = this.content,
        author = this.author,
        publishedAt = this.publishedAt,
        url = this.url,
        urlToImage = this.urlToImage,
        summary = this.summary,
        imageUrl = this.imageUrl,
        newsSite = this.newsSite,
        news_site = this.newsSite,
        published_at = this.publishedAt,
        image_url = this.imageUrl
    )
}

// Entity → Domain Model (for cached articles)
fun ArticleEntity.toArticle(): Article {
    return Article(
        id = this.id,
        title = this.title,
        description = this.description,
        content = this.content,
        author = this.author,
        publishedAt = this.publishedAt,
        url = this.url,
        urlToImage = this.urlToImage,
        summary = this.summary,
        imageUrl = this.imageUrl,
        newsSite = this.newsSite,
        news_site = this.newsSite,
        published_at = this.publishedAt,
        image_url = this.imageUrl
    )
}
