package com.kth.id2216.group3.time.data.entities

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class able to link [Category] and its multiple [Timer]
 */
data class CategoryWithTimers(
        @Embedded
        var category: Category,
        @Relation(
                parentColumn = "id",
                entityColumn = "category_id"
        )
        var timers: List<Timer>

)