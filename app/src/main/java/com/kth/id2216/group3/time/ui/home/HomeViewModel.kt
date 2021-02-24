package com.kth.id2216.group3.time.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.repositories.TimerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel of the [HomeFragment]
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val timerRepository: TimerRepository
)
: ViewModel() {

    val timers: LiveData<List<Timer>> = timerRepository.getAll().asLiveData()

    fun addTimer(timer: Timer) {
        viewModelScope.launch {
            timerRepository.insert(timer)
        }
    }

    fun getAllTimers(): LiveData<List<Timer>> {
        viewModelScope.launch {
            val timers = timerRepository.getAll().asLiveData()
        }
        return timers
    }

    fun loadTimerById(id: Int): LiveData<Timer> {
            return timerRepository.loadById(id).asLiveData()
    }


}