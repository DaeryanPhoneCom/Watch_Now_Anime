package com.example.watchnowanime.repository

import com.example.watchnowanime.api.RetrofitInstance

class AnimeRepository() {

    fun getAnime(search: String) = RetrofitInstance.api.getAnime()

    fun getNewRelease(search: String) = RetrofitInstance.api.getNewRelease()

    fun getMovies(search: String) = RetrofitInstance.api.getMovies()

    suspend fun getAnimeInfo(animeId: String) = RetrofitInstance.api.getAnimeInfo(animeId)

    suspend fun getEpisodes(animeId: String) = RetrofitInstance.api.getAnimeInfo(animeId)

    suspend fun getEpisodeStream(episodeId: String) = RetrofitInstance.api.getEpisodeStream(episodeId)
}