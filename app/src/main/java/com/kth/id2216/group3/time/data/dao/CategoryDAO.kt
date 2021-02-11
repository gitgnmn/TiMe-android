package com.kth.id2216.group3.time.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kth.id2216.group3.time.data.entities.Category

@Dao
interface CategoryDAO {
    @Query("SELECT * FROM category_table ORDER BY created ASC")
    fun getAll(): LiveData<List<Category>>

    @Insert
    fun insert(category: Category)

    @Delete
    fun delete(category: Category)

    @Query("DELETE FROM category_table")
    fun deleteAll()
}