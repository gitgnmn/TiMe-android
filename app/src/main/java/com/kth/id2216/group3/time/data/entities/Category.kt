package com.kth.id2216.group3.time.data.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category (
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var name: String,
        val created: Long? = System.currentTimeMillis()
)