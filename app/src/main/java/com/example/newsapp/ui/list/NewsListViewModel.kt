package com.example.newsapp.ui.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.*

class NewsListViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val searchQuery = MutableStateFlow<String?>(null)

    val articles = searchQuery
        .debounce(400)
        .distinctUntilChanged()
        .flatMapLatest { repository.getArticles(it) }
        .cachedIn(viewModelScope)

    fun search(query: String) {
        searchQuery.value = query.takeIf { it.isNotBlank() }
    }
}
