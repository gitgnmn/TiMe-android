package com.kth.id2216.group3.time


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.kth.id2216.group3.time.MainActivity.Companion.EXTRA_TIMER_ID
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.util.TimerState
import com.kth.id2216.group3.time.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.timerTask

/**
 * Activity handing the display of a timer
 */
@AndroidEntryPoint
class TimerActivity : AppCompatActivity() {

    private lateinit var timer: Timer
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Accessing intent data
        val timerId= intent.getIntExtra(EXTRA_TIMER_ID, -1)
        Log.d("WHAT", "$timerId")
        if (timerId != -1) {
            viewModel.loadTimerById(timerId).observe(this@TimerActivity, {
                timer = it
                // Displaying the timers values
                // Set TimerName
                val tvTimerName: TextView = findViewById(R.id.textViewTimerName)
                tvTimerName.text = timer.name

                // Set TimerTime
                val tvTimerHours: TextView = findViewById(R.id.textViewProgressBarHours)
                tvTimerHours.text = "${timer.time.toHours()} h"

                val tvTimerMinutes: TextView = findViewById(R.id.textViewProgressBarMinutes)
                tvTimerMinutes.text = "${timer.time.minusHours(timer.time.toHours()).toMinutes()} m"


                // Update progressbar to 25%
                updateProgressBar(timer)

                //set toggle button
                val btnToggle: ImageButton = findViewById(R.id.buttonToggleTimer)
                btnToggle.setOnClickListener {
                    //Toast.makeText(this@TimerActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
                    toggleTimer(timer)
                    // Change img of button
                    if(timer.state != TimerState.RUNNING)
                        btnToggle.setImageResource(R.drawable.pause_icon);
                    else
                        btnToggle.setImageResource(R.drawable.play_icon);
                }

                //set back button
                val btnBack: ImageButton = findViewById(R.id.imageButtonBackToMenu)
                btnBack.setOnClickListener{
                    Log.d("Switching Activity", "MainActivity")
                    val intent = Intent(this@TimerActivity, MainActivity::class.java)
                    startActivity(intent)
                }


            })

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.timer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item -> {
                val bundle = bundleOf("timerId" to timer.id)
                findNavController(R.id.nav_host_fragment).navigate(R.id.editTimer, bundle)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun toggleTimer(timer: Timer) {
        if (timer.state != TimerState.RUNNING) {
            timer.state = TimerState.RUNNING
        } else {
            timer.state = TimerState.STOPPED
        }

        val javaTimer = java.util.Timer()

        if(timer.state == TimerState.RUNNING) {
            // Start running the timer
            javaTimer.schedule(timerTask {
                timer.time = timer.time.plusSeconds(1)

                // Set TimerTime
                val tvTimerHours: TextView = findViewById(R.id.textViewProgressBarHours) as TextView
                tvTimerHours.text = "${timer.time.toHours()} h"

                val tvTimerMinutes: TextView = findViewById(R.id.textViewProgressBarMinutes) as TextView
                val minutes = timer.time.toMinutes()
                if(minutes < 10)
                    tvTimerMinutes.text = "0${timer.time.minusHours(timer.time.toHours()).toMinutes()} m"
                else
                    tvTimerMinutes.text = "${timer.time.minusHours(timer.time.toHours()).toMinutes()} m"


                // Update progressbar
                updateProgressBar(timer)

            }, 1000, 1000)


        }
        else{
            // pause time and upload to db
            javaTimer.cancel()

            runBlocking {
                viewModel.addTimer(timer)
            }
            return
        }
    }

    fun updateProgressBar(timer: Timer) {
        val pbar = findViewById<ProgressBar>(R.id.progressBar)
        val curPer = Math.floor(((timer.time.toMinutes().toDouble() / timer.goal.toMinutes().toDouble()) * 100))
        Log.d("S", "$curPer")
        pbar.progress = curPer.toInt()
    }

}
