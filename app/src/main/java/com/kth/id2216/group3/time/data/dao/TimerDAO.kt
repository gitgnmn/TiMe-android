package com.kth.id2216.group3.time.data.dao

import androidx.room.*
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.entities.TimerWithSessions

/*
Data access object for timers
 */
@Dao
interface TimerDAO {
    @Query("SELECT * FROM timer_table")
    fun getAll(): List<Timer>

    @Transaction
    @Query("SELECT * FROM timer_table")
    fun getAllWithSessions(): List<TimerWithSessions>

    @Query("SELECT * FROM timer_table WHERE id IN (:timersIds)")
    fun loadAllByIds(timersIds: IntArray): List<Timer>

    @Insert
    fun insertAll(vararg timers: Timer)

    @Delete
    fun delete(timer: Timer)

}