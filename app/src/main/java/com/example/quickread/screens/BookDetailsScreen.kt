package com.example.quickread.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quickread.data.BookRepository

@Composable
fun BookDetailScreen(bookId: String, navController: NavController) {
    // Get the book by its title from the repository
    val book = BookRepository.getBookByTitle(bookId)

    // Show a message if the book is not found
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

        // Review section
        Text("Write a Review", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Your Review") },
            modifier = Modifier.fillMaxWidth()
        )

        // Continue Reading button
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("currentlyReading/${book.title}") }, // Navigate to CurrentlyReadingScreen
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue Reading")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Action to save the review */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Review")
        }
    }
}
