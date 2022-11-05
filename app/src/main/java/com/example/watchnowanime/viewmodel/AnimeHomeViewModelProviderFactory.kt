package com.example.watchnowanime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.watchnowanime.repository.AnimeRepository

class AnimeHomeViewModelProviderFactory(val animeRepository: AnimeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeHomeViewModel(animeRepository) as T
    }
}