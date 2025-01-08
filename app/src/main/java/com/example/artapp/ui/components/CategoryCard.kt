package com.example.artapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.artapp.data.entity.Department

@Composable
fun CategoryCard(
    department: Department,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = department.name,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Department ID: ${department.departmentId}",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
