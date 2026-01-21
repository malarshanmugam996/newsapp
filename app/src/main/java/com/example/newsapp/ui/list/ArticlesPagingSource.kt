package com.example.newsapp.ui.list


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
            // The API uses offset-based pagination
            val offset = params.key ?: 0        // start from 0
            val limit = params.loadSize         // number of items per page

            // Call API
            val response = api.getArticles(
                query = query,
                limit = limit,
                offset = offset
            )

            // Calculate next offset
            val nextKey = if (response.results.isEmpty()) {
                null
            } else {
                offset + response.results.size
            }

            LoadResult.Page(
                data = response.results,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
        }
    }
}
