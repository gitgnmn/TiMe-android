package com.kth.id2216.group3.time.ui.createtimer

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
class CreateTimerViewModel @Inject constructor(
    private val timerRepository: TimerRepository,
    private val categoryRepository: CategoryRepository
)
    : ViewModel() {

    val timers: LiveData<List<Timer>> = timerRepository.getAll().asLiveData()
    val categories: LiveData<List<Category>> = categoryRepository.getAll().asLiveData()

    fun addTimer(name: String, goal: Int) {
        viewModelScope.launch {
            val timer = Timer(name = name, goal = goal)
            timerRepository.insert(timer)
        }
    }

    fun getAllCategories(): LiveData<List<Category>> {
        viewModelScope.launch {
            val categories = categoryRepository.getAll().asLiveData()
        }
        return categories
    }
}