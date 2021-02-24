package com.kth.id2216.group3.time.ui.editTimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController

import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.data.entities.Timer
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration

@AndroidEntryPoint
class EditTimerFragment : Fragment() {

    private val viewModel: EditTimerViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_edit_timer, container, false)

        val receivedId = requireArguments().getInt("timerId")
        val timer = viewModel.getTimer(receivedId)

        val nameEditText: EditText = root.findViewById(R.id.edit_timer_name)
        val goalEditText: EditText = root.findViewById(R.id.edit_timer_goal)

        timer.observe(viewLifecycleOwner, {
            nameEditText.setText(it.name)
            goalEditText.setText(it.goal.toHours().toInt())
        })

        var items = listOf("No category")
        val adapter = ArrayAdapter<String>(requireContext(), R.layout.list_categories_dropdown)

        viewModel.getAllCategories().observe(viewLifecycleOwner, { categories ->
            if (categories.isNotEmpty()) {
                items = listOf(items, categories.map { it.name }).flatten()
                adapter.addAll(items)
            }
        }
        )

        setupEditButton(root, timer)

        setupDeleteButton(root, timer)

        return root
    }

    private fun setupEditButton(root: View, timer: LiveData<Timer>) {

        val createButton: Button = root.findViewById(R.id.edit_timer_button)
        createButton.setOnClickListener {
            val nameEditText: EditText = root.findViewById(R.id.create_timer_name)
            val goalEditText: EditText = root.findViewById(R.id.edit_timer_goal)

            // check if te value of the goal is set up and positive
            if (nameEditText.text.toString() != "" ) {
                //updating the name
                timer.observe(viewLifecycleOwner, {
                    it.name = nameEditText.text.toString()
                    it.goal = Duration.ofHours(goalEditText.text.toString().toLong())
                    viewModel.updateTimer(it)
                })
                //avoid the annoying go back on the create menu
                root.findNavController().popBackStack()
                root.findNavController().popBackStack()
                root.findNavController().navigate(R.id.nav_home)
            } else {
                val toast = Toast.makeText(this.context, getString(R.string.message_positive_goal), Toast.LENGTH_LONG)
                toast.show()
            }
        }

    }

    private fun setupDeleteButton(root: View, timer: LiveData<Timer>) {

        val deleteButton: Button = root.findViewById(R.id.delete_timer_button)
        deleteButton.setOnClickListener {
            timer.observe(viewLifecycleOwner, {
                viewModel.deleteTimer(it)
            })
            //avoid the annoying go back on the create menu
            root.findNavController().popBackStack()
            root.findNavController().popBackStack()
            root.findNavController().navigate(R.id.nav_home)
        }
    }


}