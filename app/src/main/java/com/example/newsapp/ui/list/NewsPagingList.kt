package com.example.newsapp.ui.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.data.model.Article

@Composable
fun NewsPagingList(
    items: LazyPagingItems<Article>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn {
        items(items.itemCount) { index ->
            items[index]?.let {
                NewsItemCard(
                    article = it,
                    onClick = { onItemClick(it.id) }
                )
            }
        }
    }
}
