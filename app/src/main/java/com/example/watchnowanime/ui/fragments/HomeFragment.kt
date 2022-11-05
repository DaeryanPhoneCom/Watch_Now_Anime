package com.example.watchnowanime.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.ScrollView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchnowanime.adapter.AnimeAdapter
import com.example.watchnowanime.ui.MainActivity
import com.example.watchnowanime.R
import com.example.watchnowanime.interfaces.RecyclerViewInterface
import com.example.watchnowanime.adapter.MoviesAdapter
import com.example.watchnowanime.adapter.NewReleaseAdapter
import com.example.watchnowanime.viewmodel.AnimeHomeViewModel


class HomeFragment : Fragment() , RecyclerViewInterface {

    lateinit var viewModel: AnimeHomeViewModel
    lateinit var animeAdapter: AnimeAdapter
    lateinit var newReleaseAdapter: NewReleaseAdapter
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var mContext: Context
    lateinit var recyclerView: RecyclerView
    lateinit var newReleaseRv: RecyclerView
    lateinit var moviesRv: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var scroller: ScrollView
    lateinit var detailFragment: DetailFragment


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        recyclerView = view.findViewById(R.id.home_rv)
        newReleaseRv = view.findViewById(R.id.new_releases_rv)
        moviesRv = view.findViewById(R.id.movies_rv)
        progressBar = view.findViewById(R.id.progress)
        scroller = view.findViewById(R.id.scroll)
        detailFragment = DetailFragment()
        setupRecyclerView()

        viewModel.anime.observe(viewLifecycleOwner, Observer { response ->
            animeAdapter.differ.submitList(response)
        })

        viewModel.newRelease.observe(viewLifecycleOwner, Observer { response ->
            newReleaseAdapter.differ.submitList(response)
        })

        viewModel.movies.observe(viewLifecycleOwner, Observer { response ->
            moviesAdapter.differ.submitList(response)
            progressBar.visibility = View.GONE
            scroller.visibility = View.VISIBLE
        })
    }

    private fun setupRecyclerView() {
        animeAdapter = AnimeAdapter(mContext, this)
        newReleaseAdapter = NewReleaseAdapter(mContext)
        moviesAdapter = MoviesAdapter(mContext)
        recyclerView.apply {
            adapter = animeAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        }
        newReleaseRv.apply {
            adapter = newReleaseAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        }
        moviesRv.apply {
            adapter = moviesAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onItemClick(animeId: String) {
       /* val bundle = Bundle()
        bundle.putString("id", animeId)
        detailFragment.arguments = bundle

        activity?.supportFragmentManager?.beginTransaction()
            ?.addToBackStack("details")
            ?.replace(R.id.container, detailFragment)
            ?.commit()*/

        requireActivity().findNavController(R.id.fragmentContainerView)
    }

}