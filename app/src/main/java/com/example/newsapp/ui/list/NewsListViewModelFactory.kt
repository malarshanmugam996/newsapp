package com.example.newsapp.ui.list



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.data.repository.NewsRepository

class NewsListViewModelFactory(
    private val repository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
