package com.kth.id2216.group3.time.data.util

import androidx.room.TypeConverter
import java.time.Duration

/**
 * Type converters to allow Room to reference complex data types within the DB
 */
class Converters {
    @TypeConverter
    fun timerStateToInt(state:TimerState): Int =
            state.idx

    @TypeConverter
    fun intToTimerState(idx: Int): TimerState? =
            TimerState.getByValue(idx)

    @TypeConverter
    fun durationToLong(duration: Duration): Long =
            duration.toMillis()

    @TypeConverter
    fun longToDuration(durationInMillis: Long): Duration =
            Duration.ofMillis(durationInMillis)
}