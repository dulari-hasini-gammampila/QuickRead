package com.example.quickread.data

import com.example.quickread.models.Book

object BookRepository {
    val books = listOf(
        Book("The Village in the Jungle", "Leonard Woolf", "Currently Reading", 65),
        Book("Goodnight Moon", "Margaret Wise Brown", "Want to Read", 0),
        Book("Siddhartha", " Hermann Hesse", "Finished", 100)
    )

    // Function to get a book by its title
    fun getBookByTitle(title: String): Book? {
        return books.find { it.title == title }
    }
}
