package com.example.newsapp.data.paging

import android.graphics.pdf.LoadParams
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.api.SpaceNewsApi
import com.example.newsapp.data.model.Article


class ArticlesPagingSource(
    private val api: SpaceNewsApi,
    private val query: String?
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val offset = params.key ?: 0
            val limit = params.loadSize

            val response = api.getArticles(
                limit = limit,
                offset = offset,
                query = query
            )

            LoadResult.Page(
                data = response.results,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = if (response.results.isEmpty()) null else offset + limit
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? =
        state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(state.config.pageSize)
        }
}

