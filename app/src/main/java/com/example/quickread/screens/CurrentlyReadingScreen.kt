package com.example.quickread.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CurrentlyReadingScreen(bookId: String) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Currently Reading Book ID: $bookId",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display message saying this is the reading screen
        Text(
            text = "This page will be used for reading the book.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Optional: Add a "Back to Book Details" button if needed
        Button(
            onClick = { /* Navigate back to Book Detail Screen or Home Screen */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Book Details")
        }
    }
}
