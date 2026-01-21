package com.example.newsapp.ui.preference


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PreferenceViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _sources = MutableStateFlow<List<String>>(emptyList())
    val sources = _sources.asStateFlow()

    val selectedSources = repository.selectedSources
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    fun loadSources() {
        viewModelScope.launch {
            _sources.value = repository.getSources()
        }
    }

    fun toggleSource(source: String) {
        viewModelScope.launch {
            val updated = selectedSources.value.toMutableSet()
            if (!updated.add(source)) updated.remove(source)
            repository.saveSelectedSources(updated)
        }
    }
}
