

package com.example.newsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ArticleEntity)

    @Query("SELECT * FROM articles WHERE id = :id LIMIT 1")
    suspend fun getArticle(id: Int): ArticleEntity?

    @Query("SELECT * FROM articles WHERE id = :id LIMIT 1")
    suspend fun getArticleById(id: Int): ArticleEntity?






}













