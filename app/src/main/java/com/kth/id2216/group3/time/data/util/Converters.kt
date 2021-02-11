package com.kth.id2216.group3.time.data.util

import androidx.room.TypeConverter

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
}