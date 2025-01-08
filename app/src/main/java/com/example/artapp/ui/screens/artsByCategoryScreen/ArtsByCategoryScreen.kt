package com.example.artapp.ui.screens.artsByCategoryScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.artapp.data.entity.ArtObject
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ArtsByCategoryScreen(
    navController: NavController,
    categoryName: String,
    viewModel: ArtsByCategoryViewModel = koinViewModel { parametersOf(categoryName) }
) {
    val artObjects by viewModel.artObjects.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Arts in Department $categoryName") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "Error while downloading arts",
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(artObjects) { artObject ->
                            ArtObjectItem(artObject) {
                                navController.navigate("artDetails_screen/${artObject.objectID}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ArtObjectItem(artObject: ArtObject, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onItemClick() },
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            artObject.imageUrl?.let { url ->
                Image(
                    painter = rememberAsyncImagePainter(url),
                    contentDescription = artObject.title,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 16.dp)
                )
            }
            Column {
                Text(text = artObject.title ?: "Без назви", style = MaterialTheme.typography.h6)
                Text(text = artObject.artist ?: "Невідомий художник", style = MaterialTheme.typography.body1)
                Text(text = artObject.objectDate ?: "", style = MaterialTheme.typography.caption)
            }
        }
    }
}
