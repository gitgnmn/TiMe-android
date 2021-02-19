package com.kth.id2216.group3.time.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kth.id2216.group3.time.data.entities.Category
import com.kth.id2216.group3.time.data.repositories.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel of the [CategoriesFragment]
 */
@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    val categories: LiveData<List<Category>> = categoryRepository.getAll().asLiveData()

    fun addCategory(category: Category) {
        viewModelScope.launch {
            categoryRepository.insert(category)
        }
    }

    fun getAllCategories(): LiveData<List<Category>> {
        viewModelScope.launch {
            val categories = categoryRepository.getAll().asLiveData()
        }
        return categories
    }
}