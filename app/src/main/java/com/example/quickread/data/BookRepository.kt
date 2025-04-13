package com.example.quickread.data


object BookRepository {
    // Make books private and expose through functions
    private val _books = listOf(
        Book(
            id = "1",
            title = "The Village in the Jungle",
            author = "Leonard Woolf",
            genre = "Fiction",
            description = "A classic novel about life in a Sri Lankan village...",
            status = "Currently Reading",
            progress = 65
        ),
        Book(
            id = "2",
            title = "Sample Book 2",
            author = "Author 2",
            description = "Another sample book description",
            genre = "Non-Fiction"
        )
    )

    fun getBooks(): List<Book> = _books
    fun getBookById(id: String): Book? = _books.find { it.id == id }
    fun getBooksByGenre(genre: String): List<Book> = _books.filter { it.genre.equals(genre, ignoreCase = true) }
    fun getRecommendedBooks(): List<Book> = _books.shuffled().take(5)
}

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val genre: String,
    val status: String = "Available",
    val progress: Int = 0,
    val isFavorite: Boolean = false
)