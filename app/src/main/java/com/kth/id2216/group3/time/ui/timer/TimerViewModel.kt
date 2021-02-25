package com.kth.id2216.group3.time.ui.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.repositories.TimerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
        private val timerRepository: TimerRepository
) : ViewModel() {

    fun loadTimerById(id: Int): LiveData<Timer> {
        return timerRepository.loadById(id).asLiveData()
    }

    fun updateTimer(timer: Timer) {
        viewModelScope.launch {
            timerRepository.insert(timer)
        }
    }

    fun goalFormated(timer: Timer): String {
        return "Goal: ${timer.goal.toHours()}h"
    }

    fun timeFormated(timer: Timer): String {
        val h = timer.time.toHours()
        val m = timer.time.minusHours(timer.time.toHours()).toMinutes()
        if (m < 1)
            return "$h h"
        else
            return "$h h\n $m m"
    }

}