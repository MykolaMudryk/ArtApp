package com.example.artapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.artapp.data.entity.ArtObject
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import coil.compose.rememberAsyncImagePainter

@Composable
fun ArtList(
    artObjects: List<ArtObject>,
    onArtObjectClick: (ArtObject) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(artObjects) { artObject ->
            ArtObjectItem(artObject = artObject, onClick = { onArtObjectClick(artObject) })
        }
    }
}

@Composable
fun ArtObjectItem(
    artObject: ArtObject,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
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
                Text(text = artObject.title ?: "No Title", style = MaterialTheme.typography.h6)
                Text(text = artObject.artist ?: "Unknown Artist", style = MaterialTheme.typography.body1)
                Text(text = artObject.objectDate ?: "", style = MaterialTheme.typography.caption)
            }
        }
    }
}
