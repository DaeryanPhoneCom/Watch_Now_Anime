package com.example.watchnowanime.api

import com.example.watchnowanime.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface AnimeApi {

    @GET("popular")
    fun getAnime(
       // @Query("keyw") search: String
    ): Call<List<AnimeItem>>

    @GET("recent-release")
    fun getNewRelease(
        // @Query("keyw") search: String
    ): Call<List<NewReleaseItem>>

    @GET("anime-movies")
    fun getMovies(
        // @Query("keyw") search: String
    ): Call<List<AnimeItem>>

    @GET("anime-details/{id}")
    suspend fun getAnimeInfo(
       @Path("id") animeId: String
    ): Response<AnimeDetails>

    @GET("vidcdn/watch/{id}")
    suspend fun getEpisodeStream(
        @Path("id") episodeId: String
    ): Response<EpisodeStream>









}