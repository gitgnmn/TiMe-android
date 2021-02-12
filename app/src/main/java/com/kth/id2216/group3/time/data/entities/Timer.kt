package com.kth.id2216.group3.time.data.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kth.id2216.group3.time.data.util.TimerState

/**
 * Represents a timer
 */
@Entity(tableName = "timer_table")
data class Timer(
        @PrimaryKey
        val id: Int,
        @ColumnInfo(name="name")
        var name: String,
        @ColumnInfo(name="category_id")
        var categoryId: Int?,
        @ColumnInfo(name="state")
        var state: TimerState = TimerState.STOPPED,
        @ColumnInfo(name="created")
        val created: Long = System.currentTimeMillis(),
        @ColumnInfo(name = "last_used")
        var lastUsed: Long = created,
        var goal: Int = 0,
        var hours: Int = 0
)