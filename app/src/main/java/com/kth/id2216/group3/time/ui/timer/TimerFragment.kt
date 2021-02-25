package com.kth.id2216.group3.time.ui.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.util.TimerState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.timerTask
import kotlin.math.floor

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private val viewModel: TimerViewModel by viewModels()
    private lateinit var timer: Timer
    private var javaTimer = java.util.Timer()

    companion object {
        const val KEY_TIMER_ID = "timerId"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) : View? {
        super.onCreate(savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_timer, container, false)


        // Accessing intent data
        val receivedTimerId= requireArguments().getInt(KEY_TIMER_ID)
        viewModel.loadTimerById(receivedTimerId).observe(viewLifecycleOwner, {

            timer = it

            // Displaying the timers values
            // Set TimerName
            val tvTimerName: TextView = root.findViewById(R.id.textViewTimerName)
            tvTimerName.text = timer.name

            // Set TimerTime
            val tvTimerHours: TextView = root.findViewById(R.id.textViewProgressBarTime)
            tvTimerHours.text = "${timer.time.toHours()} h"

            val tvTimerGoal: TextView = root.findViewById(R.id.textViewGoal)
            tvTimerGoal.text = "${timer.time.toHours()} h"

            updateProgressBar(root, timer)

            //set toggle button
            val btnToggle: ImageButton = root.findViewById(R.id.buttonToggleTimer)
            btnToggle.setOnClickListener {
                toggleTimer(root, timer)
                // Change img of button
                if (timer.state == TimerState.RUNNING)
                    btnToggle.setImageResource(R.drawable.pause_icon)
                else
                    btnToggle.setImageResource(R.drawable.play_icon)
            }

            //set settings button
            val btnSettings: ImageButton = root.findViewById(R.id.buttonSettings)
            btnSettings.setOnClickListener {
                val bundle = bundleOf(KEY_TIMER_ID to timer.id)
                findNavController().navigate(R.id.editTimer, bundle)
            }
        })
        return root
        }


    fun toggleTimer(root: View, timer: Timer) {

        timer.toggle()

        if (timer.state == TimerState.RUNNING) {
            // Start running the timer
            javaTimer.schedule(timerTask {
                //TODO: change this to plusSeconds
                timer.time = timer.time.plusMinutes(1)

                // Set TimerTime
                val tvTimerHours: TextView = root.findViewById(R.id.textViewProgressBarTime)
                tvTimerHours.text = viewModel.timeFormated(timer)

                // Update progressbar
                updateProgressBar(root, timer)
            }, 1000, 1000)


        }
        else{
            // pause time and upload to db
            javaTimer.cancel()
            javaTimer = java.util.Timer()
            runBlocking {
                viewModel.updateTimer(timer)
            }
            return
        }
    }

    private fun updateProgressBar(root: View, timer: Timer) {
        val pbar = root.findViewById<ProgressBar>(R.id.progressBar)
        val curPer =
            floor(((timer.time.toMinutes().toDouble() / timer.goal.toMinutes().toDouble()) * 100))
        pbar.progress = curPer.toInt()
    }

    override fun onStop() {
        super.onStop()
        timer.state = TimerState.STOPPED
        runBlocking {
            viewModel.updateTimer(timer)
        }
    }

}