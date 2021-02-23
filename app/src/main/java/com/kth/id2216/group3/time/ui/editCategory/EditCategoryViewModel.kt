package com.kth.id2216.group3.time.ui.editCategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kth.id2216.group3.time.data.entities.Category
import com.kth.id2216.group3.time.data.repositories.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
    ) : ViewModel() {

    fun getCategory(categoryId: Int) : LiveData<Category> {
        return categoryRepository.loadById(categoryId).asLiveData()
    }

    fun updateCategory(category: Category) {
        viewModelScope.launch {
            categoryRepository.insert(category)
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            categoryRepository.delete(category)
        }
    }
}