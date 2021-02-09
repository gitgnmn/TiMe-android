package com.kth.id2216.group3.time.bo

class Timer(
    val id: Int
) {
    var name = "Nameless timer"
    var state = TimerState.STOPPED
    var goal = 0
    var progress = 0
    var sessions = arrayListOf<Session>()


    init {
        //some code to define the id
    }


}