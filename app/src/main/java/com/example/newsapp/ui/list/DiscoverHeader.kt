package com.example.newsapp.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverHeader(
    searchText: String,
    onSearchChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "Discover",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "News from all around the world",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = searchText,
            onValueChange = onSearchChange,
            placeholder = { Text("Search") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null)
            },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF2F2F2),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}
