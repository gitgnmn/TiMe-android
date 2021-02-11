package com.kth.id2216.group3.time.data.repositories

import androidx.lifecycle.LiveData
import com.kth.id2216.group3.time.data.dao.TimerDAO
import com.kth.id2216.group3.time.data.dao.CategoryDAO
import com.kth.id2216.group3.time.data.entities.Category
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.entities.TimerWithSessions
import dagger.hilt.InstallIn
import javax.inject.Inject
import javax.inject.Singleton

/*
Repository
 */

@Singleton
class TimerRepository @Inject constructor(private val timerDAO: TimerDAO) {

    //// Timer /////

    suspend fun getAll(): LiveData<List<Timer>> =
            timerDAO.getAll()

    suspend fun getAllWithSessions(): LiveData<List<TimerWithSessions>> =
            timerDAO.getAllWithSessions()

    fun loadAllByIds(timersIds: IntArray): LiveData<List<Timer>> =
            timerDAO.loadAllByIds(timersIds)

    fun insertAll(vararg timers: Timer) =
            timerDAO.insertAll(*timers)

    fun insert(timer: Timer) =
            timerDAO.insert(timer)

    fun delete(timer: Timer) =
            timerDAO.delete(timer)

    //// Categories ////
}