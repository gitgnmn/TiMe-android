package com.kth.id2216.group3.time.data.util

import com.kth.id2216.group3.time.data.util.TimerState
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [Converters]
 */
class ConvertersTest {

    @Test
    fun timerStateToInt() {
        assertEquals(0, Converters().timerStateToInt(TimerState.STOPPED))
        assertEquals(1, Converters().timerStateToInt(TimerState.PAUSED))
        assertEquals(2, Converters().timerStateToInt(TimerState.RUNNING))
    }

    @Test
    fun intToTimerState() {
        assertEquals(TimerState.STOPPED, Converters().intToTimerState(0))
        assertEquals(TimerState.PAUSED, Converters().intToTimerState(1))
        assertEquals(TimerState.RUNNING, Converters().intToTimerState(2))
    }
}