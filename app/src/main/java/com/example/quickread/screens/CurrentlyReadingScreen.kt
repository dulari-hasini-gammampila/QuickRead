package com.example.quickread.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quickread.data.BookRepository

@Composable
fun CurrentlyReadingScreen(navController: NavController) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("My Books", "Bookmarks", "Favorites")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Currently Reading", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            0 -> MyBooksTab(navController)
            1 -> BookmarksTab(navController)
            2 -> FavoritesTab(navController)
        }
    }
}

@Composable
fun MyBooksTab(navController: NavController) {
    val books = BookRepository.getBooks().filter { it.status == "Currently Reading" }

    Column {
        if (books.isEmpty()) {
            Text("No books currently being read.")
        } else {
            books.forEach { book ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("📘 ${book.title}", style = MaterialTheme.typography.titleLarge)
                        Text("Progress: ${book.progress}%", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            BookRepository.addToCurrentlyReading(book)
                            navController.navigate("reading/${book.id}")
                        }) {
                            Text("Continue Reading")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookmarksTab(navController: NavController) {
    val books = BookRepository.getBookmarkedBooks()

    Column {
        if (books.isEmpty()) {
            Text("No bookmarked books.")
        } else {
            books.forEach { book ->
                Button(
                    onClick = { navController.navigate("bookDetail/${book.id}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("🔖 ${book.title}")
                }
            }
        }
    }
}

@Composable
fun FavoritesTab(navController: NavController) {
    val books = BookRepository.getFavoriteBooks()

    Column {
        if (books.isEmpty()) {
            Text("No favorite books.")
        } else {
            books.forEach { book ->
                Button(
                    onClick = { navController.navigate("bookDetail/${book.id}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("❤️ ${book.title}")
                }
            }
        }
    }
}
