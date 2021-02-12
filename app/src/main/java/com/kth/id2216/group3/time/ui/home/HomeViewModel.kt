package com.kth.id2216.group3.time.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.repositories.TimerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun insertTimer(timer: Timer) { timerRepository.insert(timer) }
    fun insertTimerList(timerList : List<Timer>) {timerRepository.insertAll(timerList)}
}