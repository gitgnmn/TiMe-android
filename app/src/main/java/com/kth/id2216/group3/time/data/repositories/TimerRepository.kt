package com.kth.id2216.group3.time.data.repositories


import com.kth.id2216.group3.time.data.dao.TimerDAO
import com.kth.id2216.group3.time.data.entities.Timer
import javax.inject.Inject
import javax.inject.Singleton

/**
Repository for the [Timer] class
 */
@Singleton
class TimerRepository @Inject constructor(private val timerDAO: TimerDAO) {

    fun getAll() =
            timerDAO.getAll()

    fun getAllWithSessions() =
            timerDAO.getAllWithSessions()

    fun loadAllByIds(timersIds: IntArray) =
            timerDAO.loadAllByIds(timersIds)

    fun loadById(timerId: Int) =
            timerDAO.loadById(timerId)

    suspend fun insertAll(timers: List<Timer>) =
            timerDAO.insertAll(timers)

    suspend fun insert(timer: Timer) =
            timerDAO.insert(timer)

    suspend fun delete(timer: Timer) =
            timerDAO.delete(timer)

}