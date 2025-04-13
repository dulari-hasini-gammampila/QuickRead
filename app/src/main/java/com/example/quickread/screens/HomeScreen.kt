package com.example.quickread.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.quickread.data.BookRepository
import com.example.quickread.data.Book


@Composable
fun HomeScreen(navController: NavController) {
    val books = BookRepository.getBooks()  // Use the centralized book list

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("My Library", style = MaterialTheme.typography.headlineLarge)

        // Display the books the user is currently reading
        Spacer(modifier = Modifier.height(16.dp))
        Text("Currently Reading", style = MaterialTheme.typography.headlineSmall)

        LazyColumn {
            // Correctly iterate through the list of books
            items(books) { book ->
                BookItem(book = book, navController = navController)
            }
        }

        // Navigation button for recommendations
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("recommendations") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Recommendations")
        }
    }
}

@Composable
fun BookItem(book: Book, navController: NavController) {
    Button(
        onClick = { navController.navigate("bookDetail/${book.title}") }, // Pass the book title
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Text(book.title)  // Display the book title in the button
    }
}
