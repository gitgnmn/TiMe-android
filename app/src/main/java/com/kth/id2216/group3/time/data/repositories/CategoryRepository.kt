package com.kth.id2216.group3.time.data.repositories

import com.kth.id2216.group3.time.data.dao.CategoryDAO
import com.kth.id2216.group3.time.data.entities.Category
import javax.inject.Inject
import javax.inject.Singleton

/**
Repository for the [Category] class
 */
@Singleton
class CategoryRepository @Inject constructor(private val categoryDAO: CategoryDAO) {

    fun getAll() =
            categoryDAO.getAll()

    fun getAllWithTimers() =
            categoryDAO.getAllWithTimers()

    fun loadAllByIds(categoriesIds: IntArray) =
            categoryDAO.loadAllByIds(categoriesIds)

    fun loadById(categoryId: Int) =
            categoryDAO.loadById(categoryId)

    suspend fun insertAll(categories: List<Category>) =
            categoryDAO.insertAll(categories)

    suspend fun insert(category: Category) =
            categoryDAO.insert(category)

    suspend fun delete(category: Category) =
            categoryDAO.delete(category)
}