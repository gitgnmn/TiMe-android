package com.kth.id2216.group3.time.ui.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.repositories.TimerRepository
import com.kth.id2216.group3.time.data.util.TimerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.concurrent.timerTask

/**
 * ViewModel of the [..TimerActivity]
 */
@HiltViewModel
class TimerViewModel @Inject constructor(
        private val timerRepository: TimerRepository
) : ViewModel() {

    lateinit var timer: LiveData<Timer>


    fun getTimer(id: Int) {
        viewModelScope.launch {
            timer = timerRepository.get(id).asLiveData()
        }
    }

    fun toggleTimer() {
        timer.value?.state?.toggle()
        if (timer.value?.state == TimerState.RUNNING) {
            // Start running the timer
            val newTimer = java.util.Timer()
            newTimer.schedule(timerTask {
                timer.value?.hours?.plus(1)
            }, 1000, 1000)
        } else {
            // paus time and maybe here upload to db?
            //this.timer.cancel()
            return
        }
    }

    fun name(): String {
        return timer.value!!.name
    }

    fun state(): TimerState {
        return timer.value!!.state
    }

    fun progress(): Int {
        return timer.value!!.getProgress()
    }

    fun hoursText(): String {
        return timer.value!!.getHoursFormatted()
    }

    fun goalText(): String {
        return timer.value!!.getGoalFormatted()
    }
}
