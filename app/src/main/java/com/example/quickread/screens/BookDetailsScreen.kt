package com.example.quickread.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quickread.data.BookRepository

@Composable
fun BookDetailScreen(bookId: String, onReadBook: (String) -> Unit, onBack: () -> Unit) {
    val book = BookRepository.getBookById(bookId)

    if (book == null) {
        Text("Book not found", style = MaterialTheme.typography.headlineLarge)
        return
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Book Details: ${book.title}", style = MaterialTheme.typography.headlineLarge)

        // Book details
        Text("Title: ${book.title}", style = MaterialTheme.typography.bodyLarge)
        Text("Author: ${book.author}", style = MaterialTheme.typography.bodyLarge)
        Text("Status: ${book.status}", style = MaterialTheme.typography.bodyMedium)
        Text("Progress: ${book.progress}%", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Continue Reading button
        Button(
            onClick = { onReadBook(bookId) }, // Navigate to ReadingScreen
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue Reading")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }

        Button(
            onClick = {
                // Toggle favorite state - replace with real repo update later
                val updatedBook = book.copy(isFavorite = !book.isFavorite)
                // Update your BookRepository if mutable
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (book.isFavorite) "Remove from Favorites" else "Add to Favorites")
        }
        Button(
            onClick = {
                BookRepository.toggleFavorite(bookId)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (book.isFavorite) "Remove from Favorites" else "Add to Favorites")
        }


    }
}
