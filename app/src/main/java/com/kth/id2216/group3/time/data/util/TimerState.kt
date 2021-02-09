package com.kth.id2216.group3.time.data.util

enum class TimerState(val idx: Int) {
    STOPPED(0),
    PAUSED(1),
    RUNNING(2);

    companion object {
        fun getByValue(idx: Int) = values().firstOrNull { it.idx == idx }
    }
}