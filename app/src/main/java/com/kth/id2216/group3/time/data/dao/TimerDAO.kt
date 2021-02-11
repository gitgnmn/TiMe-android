package com.kth.id2216.group3.time.data.dao

import androidx.room.*
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.entities.TimerWithSessions
import kotlinx.coroutines.flow.Flow

/*
Data access object for timers
 */
@Dao
interface TimerDAO {
    @Query("SELECT * FROM timer_table ORDER BY created ASC")
    fun getAll(): Flow<List<Timer>>

    @Transaction
    @Query("SELECT * FROM timer_table")
    fun getAllWithSessions(): Flow<List<TimerWithSessions>>

    @Query("SELECT * FROM timer_table WHERE id IN (:timersIds)")
    fun loadAllByIds(timersIds: IntArray): Flow<List<Timer>>

    @Insert
    fun insertAll(vararg timers: Timer)

    @Insert
    fun insert(timer: Timer)

    @Delete
    fun delete(timer: Timer)

    @Query("DELETE FROM timer_table")
    fun deleteAll()
}