package com.kth.id2216.group3.time.ui.createtimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass handling timer creation
 */
@AndroidEntryPoint
class CreateTimerFragment : Fragment() {

    private val createTimerViewModel: CreateTimerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_timer, container, false)
    }




}