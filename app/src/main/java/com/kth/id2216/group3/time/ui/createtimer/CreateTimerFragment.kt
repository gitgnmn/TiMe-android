package com.kth.id2216.group3.time.ui.createtimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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


        val root = inflater.inflate(R.layout.fragment_create_timer, container, false)

        val createButton: Button = root.findViewById(R.id.create_timer_button)
        createButton.setOnClickListener {
            val nameEditText: EditText = root.findViewById(R.id.create_timer_name)
            val goalEditText: EditText = root.findViewById(R.id.create_timer_goal)
            val goalString = goalEditText.text.toString()

            // check if te value of the goal is set up and positive
            if (goalString != "" && Integer.parseInt(goalString) > 0) {
                createTimerViewModel.addTimer(
                        nameEditText.text.toString(),
                        Integer.parseInt(goalEditText.text.toString())
                )
                root.findNavController().navigate(R.id.nav_home)
            } else {
                val toast = Toast.makeText(this.context, getString(R.string.message_positive_goal), Toast.LENGTH_LONG)
                toast.show()
            }
        }


        return root
    }




}