package com.kth.id2216.group3.time.data.repositories

import androidx.lifecycle.LiveData
import com.kth.id2216.group3.time.data.dao.CategoryDAO
import com.kth.id2216.group3.time.data.entities.Category

import com.kth.id2216.group3.time.data.entities.CategoryWithTimers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(private val categoryDAO: CategoryDAO) {

    fun getAll(): LiveData<List<Category>> =
            categoryDAO.getAll()

    fun getAllWithTimers(): LiveData<List<CategoryWithTimers>> =
            categoryDAO.getAllWithTimers()

    fun loadAllByIds(categoriesIds: IntArray): LiveData<List<Category>> =
            categoryDAO.loadAllByIds(categoriesIds)

    fun insertAll(vararg categories: Category) =
            categoryDAO.insertAll(*categories)

    fun insert(timer: Category) =
            categoryDAO.insert(timer)

    fun delete(timer: Category) =
            categoryDAO.delete(timer)
}