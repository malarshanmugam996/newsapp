package com.example.newsapp.data.api


import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.ArticleDto
import com.example.newsapp.data.model.ArticleResponse
import com.example.newsapp.data.model.InfoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpaceNewsApi {

    @GET("articles/")
    suspend fun getArticles(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("title_contains") query: String? = null
    ): ArticleResponse

    @GET("articles/{id}/")
    suspend fun getArticleDetail(
        @Path("id") id: Int
    ): Article
    @GET("info/")
    suspend fun getInfo(): InfoResponse

    @GET("articles/{id}/")
    suspend fun getArticle(
        @Path("id") id: Int
    ): ArticleDto


}
