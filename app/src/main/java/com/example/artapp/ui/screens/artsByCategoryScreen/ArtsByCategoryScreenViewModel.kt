package com.example.artapp.ui.screens.artsByCategoryScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artapp.data.entity.ArtObject
import com.example.artapp.repository.ArtRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtsByCategoryViewModel(
    private val artRepository: ArtRepository,
    private val categoryId: Int
) : ViewModel() {

    private val _artObjects = MutableStateFlow<List<ArtObject>>(emptyList())
    val artObjects: StateFlow<List<ArtObject>> = _artObjects

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadArtsByCategory()
    }

    private fun loadArtsByCategory() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val categoryObjects = artRepository.getObjectsForDepartment(categoryId)
                if (categoryObjects != null && categoryObjects.objectIDs.isNotEmpty()) {
                    val artList = mutableListOf<ArtObject>()
                    // Обмежую кількість для демонстрації до 20
                    categoryObjects.objectIDs.take(20).forEach { id ->
                        val artObject = artRepository.getArtObjectById(id)
                        if (artObject != null) {
                            artList.add(artObject)
                        }
                    }
                    _artObjects.value = artList
                } else {
                    _errorMessage.value = "No art objects found in this department."
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "An error occurred."
            } finally {
                _isLoading.value = false
            }
        }
    }
}
