package com.example.watchnowanime.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watchnowanime.R
import com.example.watchnowanime.interfaces.RecyclerViewInterface
import com.example.watchnowanime.adapter.DetailAdapter
import com.example.watchnowanime.model.AnimeItem
import com.example.watchnowanime.ui.MainActivity
import com.example.watchnowanime.viewmodel.AnimeHomeViewModel

class DetailFragment : Fragment(), RecyclerViewInterface {

    lateinit var viewModel: AnimeHomeViewModel
    lateinit var img: ImageView
    private val animeId by navArgs<DetailFragmentArgs>()
    lateinit var episodeRv: RecyclerView
    lateinit var description: TextView
    lateinit var detailAdapter: DetailAdapter
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        img = view.findViewById(R.id.img)
        episodeRv = view.findViewById(R.id.detail_rv)
        description = view.findViewById(R.id.description_tv)
        setupRecyclerView()
        animeId.currentAnime.animeId?.let { viewModel.getAnimeInfo(it) }
        viewModel.animeInfo.observe(viewLifecycleOwner, Observer { response ->
            Glide.with(this).load(response.animeImg).into(img)
            description.text = response.synopsis
            detailAdapter.differ.submitList(response.episodesList)
        })
    }

    private fun setupRecyclerView() {
        detailAdapter = DetailAdapter(mContext, this)
        episodeRv.apply {
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onItemClick(animeId: String) {
       val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(animeId))
        startActivity(intent)
    }

}