package com.example.newsapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.toArticle
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsDetailViewModel(
    private val repository: NewsRepository,
    private val articleId: Int
) : ViewModel() {

    private val _article = MutableStateFlow<Article?>(null)
    val article: StateFlow<Article?> = _article

    init {
        loadArticle()
    }

    private fun loadArticle() {
        viewModelScope.launch {
            // 1️⃣ Try cache first (Entity -> Domain)
            _article.value = repository.getCachedArticle(articleId)

            // 2️⃣ Fetch from API (DTO -> Domain)
            try {
                val remote = repository.getArticle(articleId)
                repository.cacheArticle(remote) // save as Entity
                _article.value = remote.toArticle() // ✅ now correct type
            } catch (_: Exception) { }
        }
    }



}
