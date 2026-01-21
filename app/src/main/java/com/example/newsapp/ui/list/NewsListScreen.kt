package com.example.newsapp.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel,
    onArticleClick: (Int) -> Unit,
    onPreferenceClick: () -> Unit
) {
    val articles = viewModel.articles.collectAsLazyPagingItems()
    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Discover") },
                actions = {
                    IconButton(onClick = onPreferenceClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Preferences"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(modifier = Modifier.padding(padding)) {

            DiscoverHeader(
                searchText = searchText,
                onSearchChange = {
                    searchText = it
                    viewModel.search(it)
                }
            )

            CategoryChips(
                categories = listOf("All", "Politics", "Sport", "Education", "World"),
                selected = selectedCategory,
                onSelected = {
                    selectedCategory = it
                    viewModel.search(it)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            NewsPagingList(
                items = articles,
                onItemClick = onArticleClick
            )
        }
    }
}

