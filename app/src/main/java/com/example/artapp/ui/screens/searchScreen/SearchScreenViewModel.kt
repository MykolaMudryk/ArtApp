package com.example.artapp.ui.screens.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artapp.data.entity.ArtObject
import com.example.artapp.repository.ArtRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val artRepository: ArtRepository
) : ViewModel() {

    // Властивість для збереження запиту пошуку
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    // Властивість для збереження результатів пошуку
    private val _searchResults = MutableStateFlow<List<ArtObject>>(emptyList())
    val searchResults: StateFlow<List<ArtObject>> = _searchResults

    // Властивість для відстеження стану завантаження
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Властивість для збереження повідомлень про помилки
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Метод для оновлення запиту пошуку
    fun updateQuery(q: String) {
        _query.value = q
    }

    // Метод для виконання пошуку
    fun performSearch() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = artRepository.searchArtObjects(_query.value)
                val artList = mutableListOf<ArtObject>()
                result?.objectIDs?.take(20)?.forEach { id ->
                    artRepository.getArtObjectById(id)?.let { artList.add(it) }
                }
                if (artList.isNotEmpty()) {
                    _searchResults.value = artList
                } else {
                    _errorMessage.value = "No art objects found for '${_query.value}'."
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "An error occurred."
            } finally {
                _isLoading.value = false
            }
        }
    }
}
