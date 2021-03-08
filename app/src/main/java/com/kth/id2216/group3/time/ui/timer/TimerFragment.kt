package com.kth.id2216.group3.time.ui.timer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import com.kth.id2216.group3.time.MainActivity
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.util.TimerState
import com.kth.id2216.group3.time.ui.timer.TimerViewModel.Companion.goalFormated
import com.kth.id2216.group3.time.ui.timer.TimerViewModel.Companion.timeFormated
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.timerTask
import kotlin.math.floor


@AndroidEntryPoint
class TimerFragment : Fragment() {

    //private val CHANNEL_ID: String? = "1337"
    private val viewModel: TimerViewModel by viewModels()
    private lateinit var timer: Timer
    private var javaTimer = java.util.Timer()
    private lateinit var notifManager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder

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

        // Create channel for notification
        createChannel()

        // Accessing intent data
        val receivedTimerId = requireArguments().getInt(KEY_TIMER_ID)
        viewModel.loadTimerById(receivedTimerId).observe(viewLifecycleOwner, {

            timer = it

            // Displaying the timers values
            // Set TimerName
            val tvTimerName: TextView = root.findViewById(R.id.textViewTimerName)
            tvTimerName.text = timer.name

            // Set TimerTime
            val tvTimerHours: TextView = root.findViewById(R.id.textViewProgressBarTime)
            tvTimerHours.text = timeFormated(timer)

            val tvTimerGoal: TextView = root.findViewById(R.id.textViewGoal)
            tvTimerGoal.text = goalFormated(timer)

            updateProgressBar(root, timer)

            //set toggle button
            val btnToggle: Button = root.findViewById(R.id.buttonToggleTimer)
            btnToggle.setOnClickListener {
                toggleTimer(root, timer)
                // Change img of button
                if (timer.state == TimerState.RUNNING) {
                    btnToggle.text = "Pause Timer"
                    btnToggle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pause_icon, 0, 0, 0)
                    // Create notification of current timer
                    createNotification(timer.name, timeFormated(timer))
                } else {
                    btnToggle.text = "Start Timer"
                    btnToggle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.play_icon, 0, 0, 0)
                }
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


    private fun toggleTimer(root: View, timer: Timer) {

        timer.toggle()

        if(timer.state == TimerState.RUNNING) {
            // Start running the timer
            javaTimer.schedule(timerTask {
                activity?.runOnUiThread(Runnable {
                    val tvTimerTime: TextView = root.findViewById(R.id.textViewProgressBarTime)
                    timer.time = timer.time.plusSeconds(1)
                    tvTimerTime.text = timeFormated(timer)
                    updateProgressBar(root, timer)
                    // update notification
                    val notificationId = 1010
                    updateNotification(notificationId, timeFormated(timer))
                })

            }, 1000, 1000)

        }
        else{
            // pause time and upload to db
            javaTimer.cancel()
            javaTimer = java.util.Timer()
            runBlocking {
                viewModel.updateTimer(timer)
            }
            // Remove the notification
            val notificationId = 1010
            this.notifManager.cancel(notificationId);

            return
        }
    }

    private fun updateProgressBar(root: View, timer: Timer) {
        val pbar = root.findViewById<ProgressBar>(R.id.progressBar)
        val curPer = floor(((timer.time.toMinutes().toDouble() / timer.goal.toMinutes().toDouble()) * 100))
        pbar.progress = curPer.toInt()
    }

    override fun onStop() {
        super.onStop()
        timer.state = TimerState.STOPPED
        runBlocking {
            viewModel.updateTimer(timer)
        }
        // Remove the notification
        val notificationId = 1010
        this.notifManager.cancel(notificationId);
    }

    // Creates and displays a notification of an active timer
    private fun createNotification(name: String, time: String){
        val channelId = "timerNotification"
        val context = this.requireActivity().applicationContext!!
        val notificationId = 1010

        // Create an explicit intent for an Activity in your app
        val notificationIntent = Intent(context, MainActivity::class.java).apply {
            //flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        // To open fragment back up again when clicking notification
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0)

        // Creates builder for notification
        var builder = NotificationCompat.Builder(context, channelId)
        builder.setContentTitle(name)
        builder.setContentText("$time : press to stop timer")
        builder.setSmallIcon(R.drawable.ic_launcher_background)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
        builder.setAutoCancel(true)

        this.builder = builder

        // Show the notification
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
    }

    private fun updateNotification(notificationId: Int, time: String){
        // Update notification to show current time
        this.builder.setContentText("$time : press to stop timer")
        this.notifManager.notify(notificationId, this.builder.build());
    }

    private fun createChannel() {
        val channelId = "timerNotification"
        val context = this.requireActivity().applicationContext!!
        // Create the NotificationChannel, but only on API 26+ because
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // Create the channel with data above
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            this.notifManager = notificationManager
        }
    }

}