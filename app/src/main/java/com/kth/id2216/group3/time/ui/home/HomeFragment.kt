package com.kth.id2216.group3.time.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.adapters.Adapter
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.repositories.TimerRepository
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Time
import javax.inject.Inject

/**
 * Fragment handling the list of timers in the home
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Insert example data
        val timer1 = Timer(name = "Best Course Ever",goal = 140)
        val timer2 = Timer(name = "Example Course",goal = 120, hours = 10)
        val timer3 = Timer(name = "Best Course Ever",goal = 20, hours = 2)
        //homeViewModel.timerRepository.insertAll(listOf(timer1, timer2, timer3))
        //homeViewModel.timerRepository.insert(timer1)


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