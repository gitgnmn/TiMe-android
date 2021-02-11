package com.kth.id2216.group3.time.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.adapters.Adapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val timers = homeViewModel.timers

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView  = root.findViewById(R.id.timerRecyclerView)

        // Setting the layout as Staggered Grid for vertical orientation
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager

        // Sending reference and data to Adapter
        val adapter = Adapter(context, timers)
        recyclerView.adapter = adapter
        return root
    }
}