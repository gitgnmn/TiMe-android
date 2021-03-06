package com.kth.id2216.group3.time.ui.createcategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kth.id2216.group3.time.data.entities.Category
import com.kth.id2216.group3.time.data.repositories.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCategoryViewModel @Inject constructor(
private val categoryRepository: CategoryRepository
): ViewModel() {

    fun addCategory(name: String) {
        viewModelScope.launch {
            val category = Category(name = name)
            categoryRepository.insert(category)

        }
    }

}