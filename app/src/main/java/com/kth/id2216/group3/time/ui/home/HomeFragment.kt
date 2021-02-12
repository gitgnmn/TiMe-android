package com.kth.id2216.group3.time.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.adapters.TimerAdapter
import com.kth.id2216.group3.time.data.entities.Timer
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment handling the list of timers in the home
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Insert example data
        val timer1 = Timer(id = 1, name = "Best Course Ever",goal = 140)
        val timer2 = Timer(id = 2, name = "Example Course",goal = 120, hours = 10)
        val timer3 = Timer(id = 3, name = "Best Course Ever",goal = 20, hours = 2)
        homeViewModel.addTimer(timer1)
        homeViewModel.addTimer(timer2)
        homeViewModel.addTimer(timer3)

        val timersFake = MutableLiveData(listOf(timer1, timer2, timer3))

        //get data
        val timers = homeViewModel.getAllTimers()

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView  = root.findViewById(R.id.timerRecyclerView)

        // Setting the layout as Staggered Grid for vertical orientation
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager

        // Sending reference and data to Adapter
        recyclerView.adapter = TimerAdapter(context, timersFake)
        return root
    }

}