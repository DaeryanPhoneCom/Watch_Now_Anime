package com.example.watchnowanime.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.watchnowanime.R
import com.example.watchnowanime.viewmodel.AnimeHomeViewModel
import com.example.watchnowanime.viewmodel.AnimeHomeViewModelProviderFactory
import com.example.watchnowanime.ui.fragments.HomeFragment
import com.example.watchnowanime.repository.AnimeRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: AnimeHomeViewModel
    lateinit var animeHomeFragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        animeHomeFragment = HomeFragment()

        val animeRepository = AnimeRepository()
        val viewModelProviderFactory = AnimeHomeViewModelProviderFactory(animeRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(AnimeHomeViewModel::class.java)
    }


}