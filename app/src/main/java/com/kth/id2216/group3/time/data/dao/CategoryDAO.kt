package com.kth.id2216.group3.time.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kth.id2216.group3.time.data.entities.Category
import com.kth.id2216.group3.time.data.entities.CategoryWithTimers

@Dao
interface CategoryDAO {
    @Query("SELECT * FROM category_table ORDER BY created ASC")
    fun getAll(): LiveData<List<Category>>

    @Transaction
    @Query("SELECT * FROM category_table")
    fun getAllWithTimers(): LiveData<List<CategoryWithTimers>>

    @Query("SELECT * FROM category_table WHERE id IN (:categoriesIds)")
    fun loadAllByIds(categoriesIds: IntArray): LiveData<List<Category>>

    @Insert
    fun insertAll(vararg categories: Category)
    
    @Insert
    fun insert(category: Category)

    @Delete
    fun delete(category: Category)

    @Query("DELETE FROM category_table")
    fun deleteAll()
}