package com.kth.id2216.group3.time.data.dao

import androidx.room.*
import com.kth.id2216.group3.time.data.entities.Category
import com.kth.id2216.group3.time.data.entities.CategoryWithTimers
import kotlinx.coroutines.flow.Flow

/**
    Data Access Object for [Category] class
 **/
@Dao
interface CategoryDAO {
    @Query("SELECT * FROM category_table ORDER BY created ASC")
    fun getAll(): Flow<List<Category>>

    @Transaction
    @Query("SELECT * FROM category_table")
    fun getAllWithTimers(): Flow<List<CategoryWithTimers>>

    @Query("SELECT * FROM category_table WHERE id IN (:categoriesIds)")
    fun loadAllByIds(categoriesIds: IntArray): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<Category>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("DELETE FROM category_table")
    suspend fun deleteAll()
}