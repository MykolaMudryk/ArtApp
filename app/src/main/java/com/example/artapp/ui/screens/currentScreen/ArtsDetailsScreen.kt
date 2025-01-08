package com.example.artapp.ui.screens.currentScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.artapp.data.entity.ArtObject
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ArtDetailsScreen(
    navController: NavController,
    objectId: Int,
    viewModel: ArtsDetailsViewModel = getViewModel { parametersOf(objectId) }
) {
    val artObject by viewModel.artObject.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(artObject?.title ?: "Art Details") },
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
        if (artObject == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            ArtDetailsContent(artObject = artObject!!, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun ArtDetailsContent(artObject: ArtObject, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        artObject.imageUrl?.let { url ->
            Image(
                painter = rememberAsyncImagePainter(url),
                contentDescription = artObject.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(text = artObject.title ?: "Без назви", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Художник: ${artObject.artist ?: "Невідомий"}", style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Дата: ${artObject.objectDate ?: "Невідома"}", style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(4.dp))
    }
}
