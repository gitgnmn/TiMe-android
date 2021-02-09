package com.kth.id2216.group3.time.data.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kth.id2216.group3.time.data.util.TimerState


@Entity(tableName = "timer_table")
data class Timer(
        @PrimaryKey
        val id: Int,
        @ColumnInfo(name="name")
        var name: String?,
        @ColumnInfo(name="state")
        var state: TimerState,
        @ColumnInfo(name="goal")
        var goal: Int
)