package com.example.quickread.models

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val genre: String,
    val description: String,
    val status: String = "Available",
    val progress: Int = 0,                  // Percentage of progress
    val isFavorite: Boolean = false,        // Favorite toggle
    val bookmarked: Boolean = false,        // Bookmark toggle
    val lastPageRead: Int = 0,              // Last page number read
    val coverImage: String = "cover1",      // Name of drawable image (without extension)
    val story: List<String> = listOf()      // Full story pages
)
