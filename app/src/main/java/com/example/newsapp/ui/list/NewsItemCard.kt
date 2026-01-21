package com.example.newsapp.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.data.model.Article

@Composable
fun NewsItemCard(
    article: Article,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {

            AsyncImage(
                model = article.image_url,
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                article.newsSite?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Author + Date
                Text(
                    text = listOfNotNull(
                      "",
                        article.publishedAt
                    ).joinToString(" • "),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

            }
        }
    }
}
