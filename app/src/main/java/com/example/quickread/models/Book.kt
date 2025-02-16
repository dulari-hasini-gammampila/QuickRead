package com.example.quickread.models

data class Book(
    val title: String,
    val author: String,
    val status: String, // e.g., "Currently Reading", "Want to Read", "Finished"
    val progress: Int // Progress percentage (0-100)
)