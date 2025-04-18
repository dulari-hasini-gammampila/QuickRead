package com.example.quickread.models

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val genre: String,
    val status: String = "Available",
    val progress: Int = 0,
    val isFavorite: Boolean = false,
    val bookmarked: Boolean = false  // ✅ Add this line
)
