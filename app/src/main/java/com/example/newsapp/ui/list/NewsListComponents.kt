package com.example.newsapp.ui.list

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.*
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.newsapp.data.model.Article

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullToRefreshPagingList(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<Article>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items.itemCount) { index ->
            val article = items[index]
            if (article != null) {
                NewsCard(
                    article = article,
                    onClick = { onItemClick(article.id) }
                )
            }
        }
    }
}

@Composable
fun NewsCard(
    article: Article,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(article.id) },
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {

            AsyncImage(
                model = article.image_url,
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                article.news_site?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
