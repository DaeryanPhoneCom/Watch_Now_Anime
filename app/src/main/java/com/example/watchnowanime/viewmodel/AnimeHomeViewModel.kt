package com.example.watchnowanime.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchnowanime.model.AnimeDetails
import com.example.watchnowanime.model.AnimeItem
import com.example.watchnowanime.model.Episodes
import com.example.watchnowanime.model.NewReleaseItem
import com.example.watchnowanime.repository.AnimeRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeHomeViewModel(
    val animeRepository: AnimeRepository
): ViewModel() {

    val anime: MutableLiveData<List<AnimeItem>> = MutableLiveData()
    val newRelease: MutableLiveData<List<NewReleaseItem>> = MutableLiveData()
    val movies: MutableLiveData<List<AnimeItem>> = MutableLiveData()
    val animeInfo: MutableLiveData<AnimeDetails> = MutableLiveData()
    val episodes: MutableLiveData<List<Episodes>> = MutableLiveData()
    var animePage = 1

    init {
        getAnime()
        getNewRelease()
        getMovies()
    }

    fun getAnime() = viewModelScope.launch {
        val call = animeRepository.getAnime("naruto")
        call.enqueue(object: Callback<List<AnimeItem>>{

            override fun onResponse(
                call: Call<List<AnimeItem>>,
                response: Response<List<AnimeItem>>
            ) {
                if (response.isSuccessful){
                    anime.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<AnimeItem>>, t: Throwable) {
                Log.d("daeryan", t.message.toString())
            }
        })

    }

    fun getNewRelease() = viewModelScope.launch {
        val call = animeRepository.getNewRelease("test")

        call.enqueue(object : Callback<List<NewReleaseItem>>{
            override fun onResponse(
                call: Call<List<NewReleaseItem>>,
                response: Response<List<NewReleaseItem>>
            ) {
                if (response.isSuccessful){
                    newRelease.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<NewReleaseItem>>, t: Throwable) {
                Log.d("daeryan", t.message.toString())
            }
        })
    }

    fun getMovies() = viewModelScope.launch {
        val call = animeRepository.getMovies("n")

        call.enqueue(object : Callback<List<AnimeItem>>{
            override fun onResponse(
                call: Call<List<AnimeItem>>,
                response: Response<List<AnimeItem>>
            ) {
                if (response.isSuccessful){
                    movies.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<AnimeItem>>, t: Throwable) {
                Log.d("daeryan", t.message.toString())
            }
        })
    }

    fun getAnimeInfo(animeId: String) = viewModelScope.launch {
        val response = animeRepository.getAnimeInfo(animeId)

        if (response.isSuccessful){
            animeInfo.postValue(response.body())
        }
    }

    fun getEpisodes(animeId: String) = viewModelScope.launch {
        val response = animeRepository.getEpisodes(animeId)

        if (response.isSuccessful){
            episodes.postValue(response.body()!!.episodesList)
        }
    }
}

