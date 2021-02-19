package com.kth.id2216.group3.time


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kth.id2216.group3.time.databinding.ActivityTimerBinding
import com.kth.id2216.group3.time.ui.timer.TimerViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity handing the display of a timer
 */
@AndroidEntryPoint
class TimerActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(TimerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        val id = intent.extras?.getString("Timer id")
        val timerId = Integer.parseInt(id!!)
        Log.e("+++ GOT INTENT", id.toString())

        val binding: ActivityTimerBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_timer)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        viewModel.getTimer(timerId)
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
}