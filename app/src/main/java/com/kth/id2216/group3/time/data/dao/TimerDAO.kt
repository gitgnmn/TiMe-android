package com.kth.id2216.group3.time.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kth.id2216.group3.time.data.entities.Category
import com.kth.id2216.group3.time.data.entities.Timer
import com.kth.id2216.group3.time.data.entities.TimerWithSessions

/*
Data access object for timers
 */
@Dao
interface TimerDAO {
    @Query("SELECT * FROM timer_table ORDER BY created ASC")
    fun getAll(): LiveData<List<Timer>>

    @Transaction
    @Query("SELECT * FROM timer_table")
    fun getAllWithSessions(): LiveData<List<TimerWithSessions>>

    @Query("SELECT * FROM timer_table WHERE id IN (:timersIds)")
    fun loadAllByIds(timersIds: IntArray): LiveData<List<Timer>>

    @Insert
    fun insertAll(vararg timers: Timer)

    @Insert
    fun insert(timer: Timer)

    @Delete
    fun delete(timer: Timer)

    @Query("DELETE FROM timer_table")
    fun deleteAll()
}