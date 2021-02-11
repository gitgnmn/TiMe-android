package com.kth.id2216.group3.time.data.entities


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kth.id2216.group3.time.data.util.Converters
import com.kth.id2216.group3.time.data.util.TimerState
import java.util.*


@Entity(tableName = "timer_table")
data class Timer(
        @PrimaryKey(autoGenerate=true)
        val id: Int,
        @NonNull
        var name: String?,
       // var state: TimerState,
        val created: Int, // = System.currentTimeMillis(),
        //@ColumnInfo(name = "last_used")
        var lastUsed: Long, // = created,
        var goal: Int = 0,
        var minutes: Int = 0,
        var categories: Category
)