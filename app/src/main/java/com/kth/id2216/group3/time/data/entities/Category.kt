package com.kth.id2216.group3.time.data.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a category of timers
 */
@Entity(tableName = "category_table")
data class Category (
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var name: String,
        val created: Long? = System.currentTimeMillis()
)