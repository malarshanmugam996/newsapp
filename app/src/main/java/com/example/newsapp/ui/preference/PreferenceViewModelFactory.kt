package com.example.newsapp.ui.preference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.data.repository.NewsRepository

class PreferenceViewModelFactory(
    private val repository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreferenceViewModel::class.java)) {
            return PreferenceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
