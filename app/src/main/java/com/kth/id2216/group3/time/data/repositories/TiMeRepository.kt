package com.kth.id2216.group3.time.data.repositories

import androidx.lifecycle.LiveData
import com.kth.id2216.group3.time.data.dao.TimerDAO
import com.kth.id2216.group3.time.data.dao.CategoryDAO
import com.kth.id2216.group3.time.data.entities.Category
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.entities.TimerWithSessions

/*
Repository
 */
class TiMeRepository(private val timerDAO: TimerDAO) {

    //// Timer /////

    fun getAllTimer(): LiveData<List<Timer>> =
            timerDAO.getAll()

    fun getAllTimersWithSessions(): LiveData<List<TimerWithSessions>> =
            timerDAO.getAllWithSessions()

    fun loadAllTimersByIds(timersIds: IntArray): LiveData<List<Timer>> =
            timerDAO.loadAllByIds(timersIds)

    fun insertAllTimers(vararg timers: Timer) =
            timerDAO.insertAll(*timers)

    fun insertTimer(timer: Timer) =
            timerDAO.insert(timer)

    fun deleteTimer(timer: Timer) =
            timerDAO.delete(timer)

    //// Categories ////
}