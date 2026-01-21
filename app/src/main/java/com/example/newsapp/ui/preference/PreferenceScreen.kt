package com.example.newsapp.ui.preference


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.preferences.PreferenceItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceScreen(
    viewModel: PreferenceViewModel,
    onSave: () -> Unit
) {
    val sources by viewModel.sources.collectAsState()
    val selected by viewModel.selectedSources.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadSources()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Choose your News Sources") },
                navigationIcon = {
                    IconButton(onClick = onSave) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = onSave,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Next")
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(sources) { source ->
                PreferenceItem(
                    title = source,
                    selected = selected.contains(source),
                    onClick = { viewModel.toggleSource(source.toString()) }
                )
            }
        }
    }
}

