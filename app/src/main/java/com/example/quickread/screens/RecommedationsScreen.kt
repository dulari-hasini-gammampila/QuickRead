package com.example.quickread.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecommendationsScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Recommended for You",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )

        // Display recommended books horizontally
        LazyRow {
            items(listOf("Book 1", "Book 2", "Book 3")) { book ->
                RecommendedBookItem(book)
            }
        }
    }
}

@Composable
fun RecommendedBookItem(book: String) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(book, style = MaterialTheme.typography.headlineSmall)
        }
    }
}
