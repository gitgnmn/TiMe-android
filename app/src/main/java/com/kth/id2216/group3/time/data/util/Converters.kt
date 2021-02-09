package com.kth.id2216.group3.time.data.util

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun timerStateToInt(state:TimerState): Int =
            state.idx

    @TypeConverter
    fun intToTimerState(idx: Int): TimerState? =
            TimerState.getByValue(idx)
}