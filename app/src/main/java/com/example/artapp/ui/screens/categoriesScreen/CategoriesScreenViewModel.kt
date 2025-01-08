package com.example.artapp.ui.screens.categoriesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artapp.data.entity.Department
import com.example.artapp.repository.ArtRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesScreenViewModel(
    private val artRepository: ArtRepository
) : ViewModel() {
    private val _categories = MutableStateFlow<List<Department>>(emptyList())
    val categories: StateFlow<List<Department>> = _categories

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            val fetchedCategories = artRepository.getAllCategories()
            _categories.update { fetchedCategories ?: emptyList() }
        }
    }
}
