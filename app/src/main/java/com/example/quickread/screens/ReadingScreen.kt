package com.example.quickread.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReadingScreen(bookId: String, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Reading Book ID: $bookId", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Placeholder content for reading
        Text("Here will be the content of the book...", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Book Details")
        }

        Button(
            onClick = {
                // Toggle bookmarked state - simulate or store in repo
                // Example: BookRepository.toggleBookmark(bookId)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🔖 Bookmark This Page")
        }
        Button(
            onClick = {
                BookRepository.toggleBookmark(bookId)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            val book = BookRepository.getBookById(bookId)
            Text(if (book?.bookmarked == true) "Remove Bookmark" else "Bookmark This Page")
        }

    }
}
