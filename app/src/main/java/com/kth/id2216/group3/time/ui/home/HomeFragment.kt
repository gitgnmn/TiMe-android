package com.kth.id2216.group3.time.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.adapters.TimerAdapter
import com.kth.id2216.group3.time.data.entities.Timer
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration

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
        //val timer1 = Timer(id = 1, name = "Best Course Ever", goal = Duration.ofHours(14))
        //val timer2 = Timer(id = 2, name = "Example Course", goal = Duration.ofHours(20), time = Duration.ofHours(10))
        //val timer3 = Timer(id = 3, name = "Building some awesome robots", goal = Duration.ofHours(20), time = Duration.ofHours(1))

        val adapter = TimerAdapter(context)
        //get data
        homeViewModel.getAllTimers().observe(viewLifecycleOwner, { timers ->
                adapter.timers = timers
        })

        // Add example data to db
        //homeViewModel.addTimer(timer1)
        //homeViewModel.addTimer(timer2)
        //homeViewModel.addTimer(timer3)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView  = root.findViewById(R.id.timerRecyclerView)

        // Setting the layout as Staggered Grid for vertical orientation
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager

        // Sending reference and data to Adapter
        recyclerView.adapter = adapter

        val navController = findNavController()
        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener { navController.navigate(R.id.createTimer) }


        return root
    }
}