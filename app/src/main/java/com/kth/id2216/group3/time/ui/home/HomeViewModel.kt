package com.kth.id2216.group3.time.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kth.id2216.group3.time.data.dao.TimerDAO

import com.kth.id2216.group3.time.data.db.TiMeDatabase
import androidx.lifecycle.viewModelScope
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.repositories.TimerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    timerRepository: TimerRepository
)
: ViewModel() {

    val timers: LiveData<List<Timer>> = timerRepository.getAll().asLiveData()

}