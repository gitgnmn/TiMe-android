package com.kth.id2216.group3.time


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text
import java.util.*
import kotlin.concurrent.timerTask

/**
 * Activity handing the display of a timer
 */
@AndroidEntryPoint
class TimerActivity : AppCompatActivity() {
    //private var tvTimerName: TextView? = null
    private var timerIsRunning = 0
    private var timerTime = 135 // In minutes
    private var timerIsTitle = "TitleHere"
    private var timerGoal = 240
    private var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        // Set TimerName
        //tvTimerName = findViewById(R.id.textViewTimerName);
        val tvTimerName: TextView = findViewById(R.id.textViewTimerName) as TextView
        tvTimerName.text = "TimerNameHere"
        tvTimerName.text = timerIsTitle


        // Set TimerTime
        val tvTimerHours: TextView = findViewById(R.id.textViewProgressBarHours) as TextView
        tvTimerHours.text = "TimerHours"
        val hours = (timerTime/60)
        tvTimerHours.text = hours.toString() + "h"

        val tvTimerMinutes: TextView = findViewById(R.id.textViewProgressBarMinutes) as TextView
        tvTimerMinutes.text = "TimerMinutes"
        val minutes = (timerTime - hours*60)
        if(minutes < 10)
            tvTimerMinutes.text = "0" + minutes.toString() + "m"
        else
            tvTimerMinutes.text = minutes.toString() + "m"

        val btnToggle = findViewById(R.id.buttonToggleTimer) as ImageButton
        btnToggle.setOnClickListener {
            //Toast.makeText(this@TimerActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            toggleTimer()
            // Change img of button
            if(timerIsRunning == 1)
                btnToggle.setImageResource(R.drawable.pause_icon);
            else if(timerIsRunning == 0)
                btnToggle.setImageResource(R.drawable.play_icon);
        }

        val btnBack = findViewById(R.id.imageButtonBackToMenu) as ImageButton
        btnBack.setOnClickListener{

            Log.d("Switching Activity", "MainActivity")
            val intent = Intent(this@TimerActivity, MainActivity::class.java)
            startActivity(intent)
        }

        // Update progressbar to 25%
        val pbar = findViewById(R.id.progressBar) as ProgressBar
        pbar.setProgress(25)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.timer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item -> {
                //TODO Add code for switch to TimerEditFragment
                Toast.makeText(this, "Tapped on icon", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun toggleTimer() {
        timerIsRunning = ((timerIsRunning + 1) % 2)
        //tvTimerName.setText("Toggled")
        val tvTimerName: TextView = findViewById(R.id.textViewTimerName) as TextView
        tvTimerName.text = "Timer status: " + timerIsRunning

        if(timerIsRunning == 1) {
            // Start running the timer
            val newTimer = Timer()
            newTimer.schedule(timerTask {
                timerTime++

                // Set TimerTime
                val tvTimerHours: TextView = findViewById(R.id.textViewProgressBarHours) as TextView
                val hours = (timerTime/60)
                tvTimerHours.text = hours.toString() + "h"

                val tvTimerMinutes: TextView = findViewById(R.id.textViewProgressBarMinutes) as TextView
                val minutes = (timerTime - hours*60)
                if(minutes < 10)
                    tvTimerMinutes.text = "0" + minutes.toString() + "m"
                else
                    tvTimerMinutes.text = minutes.toString() + "m"


                // Update progressbar
                val pbar = findViewById(R.id.progressBar) as ProgressBar
                var curPer: Double
                curPer = ((timerTime.toDouble() / timerGoal.toDouble()) * 100)
                if(curPer > 100 ) curPer = 100.toDouble()
                pbar.setProgress(curPer.toInt())

            }, 1000, 1000)

            this.timer = newTimer

            //newTimer.scheduleAtFixedRate(task, 1000, 1000);
            //val taskToPerform: TimerTask = TimerHelper()
            //newTimer.schedule(taskToPerform, 1000, 2000) // Will run after 1 sec every 2 sec


            // Update the progressbar and time


        }
        else{
            // paus time and maybe here upload to db?
            this.timer.cancel()
            return
        }
    }



}

/*internal class TimerHelper : TimerTask() {
    var value = 15
    override fun run() {
            counter++
            println("Timer run Number " + counter)
    }
} 
    companion object {
        var counter = 0
    }
}*/