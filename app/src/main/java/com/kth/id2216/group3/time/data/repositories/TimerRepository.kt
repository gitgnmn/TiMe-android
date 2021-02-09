package com.kth.id2216.group3.time.data.repositories

import com.kth.id2216.group3.time.data.dao.TimerDAO
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.entities.TimerWithSessions

/*
Repository for timers
 */
class TimerRepository(private val timerDAO: TimerDAO) {

    fun getAllTimer(): List<Timer> =
            timerDAO.getAll()

    fun getAllWithSessions(): List<TimerWithSessions> =
            timerDAO.getAllWithSessions()

    fun loadAllByIds(timersIds: IntArray): List<Timer> =
            timerDAO.loadAllByIds(timersIds)

    fun insertAll(vararg timers: Timer) =
            timerDAO.insertAll(*timers)

    fun delete(timer: Timer) =
            timerDAO.delete(timer)

}