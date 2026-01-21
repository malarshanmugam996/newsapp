package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.api.SpaceNewsApi
import com.example.newsapp.data.datastore.PreferenceDataStore
import com.example.newsapp.data.local.ArticleDao

import com.example.newsapp.data.local.ArticleEntity
import com.example.newsapp.data.local.toEntity
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.ArticleDto

import com.example.newsapp.ui.list.ArticlesPagingSource
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val api: SpaceNewsApi,
    private val dao: ArticleDao,
    private val preferenceDataStore: PreferenceDataStore
) {

    fun getArticlesPager(
        selectedSources: Set<String>
    ) = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            ArticlesPagingSource(
                api = api,
                query = selectedSources
                    .takeIf { it.isNotEmpty() }
                    ?.joinToString(",")
            )
        }
    ).flow


    // ✅ PREFERENCES PART (FIXED)

    suspend fun getSources(): List<String> {
        return api.getInfo().news_sites
    }

    val selectedSources = preferenceDataStore.selectedSources

    suspend fun saveSelectedSources(sources: Set<String>) {
        preferenceDataStore.saveSources(sources)
    }

    fun getArticles(query: String?): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                ArticlesPagingSource(api, query)  // ✅ Correct name
            }
        ).flow
    }

    // In NewsRepository
    suspend fun getCachedArticle(id: Int): Article? {
        return dao.getArticleById(id)?.toArticle()
    }

    // Extension function
    fun ArticleEntity.toArticle(): Article {
        return Article(
            id = id,
            title = title,
            summary = summary,
            publishedAt = publishedAt,
            url = url,
            imageUrl = imageUrl,
            newsSite = newsSite,
            description = description,
            content = content,
            author = author,
            news_site = news_site,
            published_at = published_at,
            urlToImage = urlToImage,
            image_url = image_url,
        )
    }


    suspend fun getArticle(id: Int): ArticleDto {
        return api.getArticle(id) // fetch from API
    }

    suspend fun cacheArticle(article: ArticleDto) {
        dao.insert(article.toEntity()) // ✅ now it works
    }



}
