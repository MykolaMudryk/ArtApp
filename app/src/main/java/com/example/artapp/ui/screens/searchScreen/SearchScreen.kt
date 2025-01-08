package com.example.artapp.ui.screens.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.artapp.data.entity.ArtObject
import com.example.artapp.ui.components.ArtList
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onArtObjectClick: (ArtObject) -> Unit
) {
    // Використовуємо query з ViewModel
    val query by viewModel.query.collectAsState()

    // Використовуємо інші стани з ViewModel
    val searchResults by viewModel.searchResults.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF005195),
                elevation = 8.dp,
                modifier = Modifier.height(120.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Тепер використовуємо query з ViewModel для збереження значення
                    TextField(
                        value = query,
                        onValueChange = { newQuery ->
                            viewModel.updateQuery(newQuery)  // Оновлюємо query через ViewModel
                        },
                        placeholder = {
                            Text("Search art...", color = Color.White.copy(alpha = 0.6f))
                        },
                        textStyle = MaterialTheme.typography.body1.copy(color = Color.White),
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.Transparent),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                            viewModel.performSearch() // Запуск пошуку
                        }),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            cursorColor = Color.White,
                            textColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            placeholderColor = Color.White.copy(alpha = 0.6f)
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { viewModel.performSearch() },
                        modifier = Modifier.height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF302e7c))
                    ) {
                        Text("Search", color = Color.White)
                    }
                }
            }
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
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center),
                        color = Color(0xFF005195)
                    )
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "",
                        style = MaterialTheme.typography.body2.copy(color = Color.Red),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
                else -> {
                    ArtList(
                        artObjects = searchResults,
                        onArtObjectClick = onArtObjectClick
                    )
                }
            }
        }
    }
}
