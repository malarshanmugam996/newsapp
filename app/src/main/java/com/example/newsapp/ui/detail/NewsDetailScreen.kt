package com.example.newsapp.ui.detail


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.ui.utils.formatDate
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    viewModel: NewsDetailViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    val article by viewModel.article.collectAsState()
    val swipeOffset = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = Color.Black
    ) { padding ->

        article?.let { news ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset { IntOffset(swipeOffset.value.toInt(), 0) }
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(
                            onDragEnd = {
                                when {
                                    swipeOffset.value > 200 -> onPrevious()
                                    swipeOffset.value < -200 -> onNext()
                                }
                                scope.launch { swipeOffset.animateTo(0f) }
                            }
                        ) { _, dragAmount ->
                            scope.launch {
                                swipeOffset.snapTo(swipeOffset.value + dragAmount)
                            }
                        }
                    }
            ) {

                // 🔥 HERO IMAGE
                AsyncImage(
                    model = news.imageUrl,
                    contentDescription = news.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp),
                    contentScale = ContentScale.Crop,
                )


                // Gradient overlay
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.8f)
                                )
                            )
                        )
                )

                // 🔝 TOP ICONS
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    Row {
                        IconButton(onClick = {}) {
                            Icon(
                                Icons.Default.FavoriteBorder,
                                contentDescription = "Bookmark",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "More",
                                tint = Color.White
                            )
                        }
                    }
                }

                // 🏷 TITLE OVER IMAGE
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                text = news.news_site ?: "News",
                                color = Color.White
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = Color.Blue.copy(alpha = 0.8f)
                        )
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = news.title,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "Trending • ${formatDate(news.published_at)}",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // 📄 CONTENT CARD
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 330.dp),
                    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(20.dp)
                    ) {

                        // Source card (CNN-like)
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = news.news_site ?: "",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        Text(
                            text = news.summary ?: "",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
