package com.kth.id2216.group3.time.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kth.id2216.group3.time.data.dao.TimerDAO

import com.kth.id2216.group3.time.data.db.TiMeDatabase
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.repositories.TimerRepository


class HomeViewModel: ViewModel() {

//    private val timerRepository: TimerRepository
//    private val timers: LiveData<List<Timer>>
//
//
//    init {
//        val db = TiMeDatabase.getDatabase(getApplication()).timerDAO()
//        timerRepository = TimerRepository(db)
//        timers = timerRepository.getAll()
//    }


}