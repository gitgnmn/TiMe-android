package com.kth.id2216.group3.time.data.entities

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class able to link [Timer] and its multiple [Session]
 */
data class TimerWithSessions(
        @Embedded
        val timer: Timer,
        @Relation(
                parentColumn = "id",
                entityColumn = "timer_id"
        )
        var session: List<Session>
)
