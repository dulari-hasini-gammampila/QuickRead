package com.example.quickread.data

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val genre: String,
    val status: String = "Available",
    val progress: Int = 0,
    val isFavorite: Boolean = false,
    val bookmarked: Boolean = false // ✅ Add bookmark flag
)

object BookRepository {
    private val _books = mutableListOf(
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
    fun getBooksByGenre(genre: String): List<Book> =
        _books.filter { it.genre.equals(genre, ignoreCase = true) }

    fun getRecommendedBooks(): List<Book> = _books.shuffled().take(5)


    fun toggleFavorite(bookId: String) {
        _books.find { it.id == bookId }?.let {
            val updated = it.copy(isFavorite = !it.isFavorite)
            val index = _books.indexOf(it)
            _books[index] = updated
        }
    }

    fun toggleBookmark(bookId: String) {
        _books.find { it.id == bookId }?.let {
            val updated = it.copy(bookmarked = !it.bookmarked)
            val index = _books.indexOf(it)
            _books[index] = updated
        }
    }
    fun toggleFavorite(bookId: String)
    fun toggleBookmark(bookId: String)

    fun getFavoriteBooks(): List<Book> = _books.filter { it.isFavorite }
    fun getBookmarkedBooks(): List<Book> = _books.filter { it.bookmarked }
}
