package com.example.newsapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.data.repository.NewsRepository

class NewsDetailViewModelFactory(
    private val repository: NewsRepository,
    private val articleId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailViewModel::class.java)) {
            return NewsDetailViewModel(repository, articleId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

