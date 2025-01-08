package com.example.artapp.ui.screens.categoriesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.artapp.ui.components.CategoryCard
import org.koin.androidx.compose.getViewModel

@Composable
fun CategoriesScreen(
    viewModel: CategoriesScreenViewModel = getViewModel(),
    onCategoryClick: (String) -> Unit
) {
    val categories by viewModel.categories.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Categories",
                        style = MaterialTheme.typography.h6.copy(color = Color.White)
                    )
                },
                backgroundColor = Color(0xFF005195),
                elevation = 8.dp,
                modifier = Modifier.height(80.dp)
            )
        }
    ) { innerPadding ->
        if (categories.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                items(categories) { category ->
                    CategoryCard(department = category, onClick = { onCategoryClick(category.displayName) })
                }
            }
        }
    }
}
