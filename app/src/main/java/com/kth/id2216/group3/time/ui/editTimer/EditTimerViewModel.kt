package com.kth.id2216.group3.time.ui.editTimer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kth.id2216.group3.time.data.entities.Category
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.repositories.CategoryRepository
import com.kth.id2216.group3.time.data.repositories.TimerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTimerViewModel @Inject constructor(
    private val timerRepository: TimerRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val categories: LiveData<List<Category>> = categoryRepository.getAll().asLiveData()

    fun getTimer(timerId: Int) : LiveData<Timer> {
        return timerRepository.loadById(timerId).asLiveData()
    }

    fun updateTimer(timer: Timer) {
        viewModelScope.launch {
            timerRepository.insert(timer)
        }
    }

    fun deleteTimer(timer: Timer) {
        viewModelScope.launch {
            timerRepository.delete(timer)
        }
    }

    fun getAllCategories(): LiveData<List<Category>> {
        viewModelScope.launch {
            val categories = categoryRepository.getAll().asLiveData()
        }
        return categories
    }
}