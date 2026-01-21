package com.example.newsapp.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChips(
    categories: List<String>,
    selected: String,
    onSelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = selected == category,
                onClick = { onSelected(category) },
                label = { Text(category) }
            )
        }
    }
}
