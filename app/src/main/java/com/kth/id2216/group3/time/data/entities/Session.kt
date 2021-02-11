package com.kth.id2216.group3.time.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a session of a timer
 */
@Entity(tableName = "session_table")
data class Session(
        @PrimaryKey
        val id: Int,
        @ColumnInfo(name = "timer_id")
        val timerId: Int,
        @ColumnInfo(name = "duration")
        var duration: Int = 0
)
