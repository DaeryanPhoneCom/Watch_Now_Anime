package com.example.watchnowanime.model

data class AnimeDetails(
    val animeImg: String,
    val animeTitle: String,
    val episodesList: List<Episodes>,
    val genres: List<String>,
    val otherNames: String,
    val releasedDate: String,
    val status: String,
    val synopsis: String,
    val totalEpisodes: String,
    val type: String
)