package com.example.newsapp.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("news_preferences")

class PreferenceDataStore(private val context: Context) {

    private val SOURCES_KEY = stringSetPreferencesKey("selected_sources")

    val selectedSources: Flow<Set<String>> =
        context.dataStore.data.map { prefs ->
            prefs[SOURCES_KEY] ?: emptySet()
        }

    suspend fun saveSources(sources: Set<String>) {
        context.dataStore.edit { prefs ->
            prefs[SOURCES_KEY] = sources
        }
    }
}
