package com.example.netflixclone.data.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoriteViewModelFactory(private val repo: FavoriteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModel(repo) as T
    }
}
