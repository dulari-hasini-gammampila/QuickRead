package com.example.quickread.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quickread.data.BookRepository
import com.example.quickread.utils.getDrawableId


@Composable
fun BookDetailScreen(
    bookId: String,
    onReadBook: (String) -> Unit,
    onBack: () -> Unit
) {
    val book = BookRepository.getBookById(bookId)

    if (book == null) {
        Text("Book not found", style = MaterialTheme.typography.headlineMedium)
        return
    }

    var rating by remember { mutableIntStateOf(0) }
    var comment by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(book.title, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = getDrawableId(book.coverImage)),
            contentDescription = book.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("Author: ${book.author}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        Text("Genre: ${book.genre}", style = MaterialTheme.typography.bodyMedium)
        Text("Progress: ${book.progress}%", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onReadBook(book.id) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📖 Continue Reading")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { BookRepository.toggleFavorite(book.id) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = if (book.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(if (book.isFavorite) "Remove from Favorites" else "Add to Favorites")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Rate this book:", style = MaterialTheme.typography.titleMedium)
        Row {
            (1..5).forEach { index ->
                IconToggleButton(checked = rating >= index, onCheckedChange = {
                    rating = index
                }) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.btn_star_big_on),
                        contentDescription = "Star $index",
                        tint = if (rating >= index) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text("Leave a comment") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                comment = ""
                rating = 0
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit")
        }
    }
}

