package com.example.artapp.ui.screens.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artapp.data.entity.ArtObject
import com.example.artapp.repository.ArtRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val artRepository: ArtRepository
) : ViewModel() {

    private val _artObjects = MutableStateFlow<List<ArtObject>>(emptyList())
    val artObjects: StateFlow<List<ArtObject>> = _artObjects

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchArt(query: String = "flower") {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val searchResponse = artRepository.searchArtObjects(query)

                // Безпечний виклик для перевірки на null
                if (!searchResponse?.objectIDs.isNullOrEmpty()) {
                    val artList = mutableListOf<ArtObject>()
                    // Якщо searchResponse не null і objectIDs не пустий
                    for (id in searchResponse!!.objectIDs.take(10)) {
                        artRepository.getArtObjectById(id)?.let { artObject ->
                            artList.add(artObject)
                        }
                    }
                    _artObjects.value = artList
                } else {
                    _errorMessage.value = "No art objects found."
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "An error occurred."
            } finally {
                _isLoading.value = false
            }
        }
    }

}
