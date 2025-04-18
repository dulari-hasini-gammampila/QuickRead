package com.example.quickread.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickread.data.BookRepository
import com.example.quickread.viewmodel.ThemeViewModel

@Composable
fun ReadingScreen(
    bookId: String,
    onBack: () -> Unit,
    themeViewModel: ThemeViewModel
) {
    val book = remember { BookRepository.getBookById(bookId) }
    var currentPage by remember { mutableIntStateOf(book?.lastPageRead ?: 0) }
    val isDarkMode by themeViewModel.isDarkMode.collectAsState()

    val backgroundColor by animateColorAsState(
        if (isDarkMode) Color(0xFF121212) else Color.White, label = ""
    )
    val textColor by animateColorAsState(
        if (isDarkMode) Color(0xFFE0E0E0) else Color.Black, label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onBack) {
                Text("Back")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🌙", fontSize = 16.sp)
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { themeViewModel.toggleTheme() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = animateColorAsState(if (isDarkMode) Color.Yellow else Color.Gray, label = "").value
                    )
                )
                Text("☀️", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Page ${currentPage + 1} / ${book?.story?.size ?: 0}",
            color = textColor,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = book?.story?.getOrNull(currentPage) ?: "No content available.",
            fontSize = 24.sp,
            lineHeight = 36.sp,
            color = textColor,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { if (currentPage > 0) currentPage-- }) {
                Text("Previous")
            }
            Button(onClick = {
                if (currentPage < (book?.story?.size ?: 1) - 1) currentPage++
            }) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                book?.let { BookRepository.updateProgress(it.id, currentPage) }
            }) {
                Text("✅ Save Progress")
            }
            Button(onClick = {
                book?.let {
                    BookRepository.bookmarkPage(it.id, currentPage)
                    BookRepository.toggleBookmark(it.id) // This marks the book as bookmarked
                }
            }) {
                Text("🔖 Bookmark")
            }

        }
    }
}
