package com.example.artapp.ui.screens.currentScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artapp.data.entity.ArtObject
import com.example.artapp.repository.ArtRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArtsDetailsViewModel(
    private val artRepository: ArtRepository,
    private val objectId: Int
) : ViewModel() {

    private val _artObject = MutableStateFlow<ArtObject?>(null)
    val artObject: StateFlow<ArtObject?> = _artObject

    init {
        loadArtDetails()
    }

    private fun loadArtDetails() {
        viewModelScope.launch {
            val art = artRepository.getArtObjectById(objectId)
            _artObject.update { art }
        }
    }
}
