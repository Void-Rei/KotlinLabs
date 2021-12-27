package eltech.kotlin.lab5

import java.time.Year

data class Book(
    val title: String,
    val author: Author,
    val genre: Genre,
    val year: Year,
) {
    override fun toString(): String {
        return "Title: $title\nAuthor: $author\nGenre: $genre\nYear:$year"
    }
}

data class Author(
    val firstName: String,
    val surname: String,
) {
    override fun toString(): String {
        return "Author: $firstName $surname"
    }
}

data class User(
    val firstName: String,
    val surname: String,
) {
    override fun toString(): String {
        return "User: $firstName $surname"
    }
}

enum class Genre {
    Detective,
    Classic,
    Fantasy,
    ScienceFiction,
    Horror,
    Poetry
}

sealed class Status {
    object Available : Status() {
        override fun toString(): String {
            return "Available"
        }
    }

    data class UsedBy(val user: User) : Status() {
        override fun toString(): String {
            return "Used by $user"
        }
    }

    object ComingSoon : Status() {
        override fun toString(): String {
            return "Coming Soon"
        }
    }

    object Restoration : Status() {
        override fun toString(): String {
            return "On Restoration"
        }
    }
}

interface LibraryInterface {
    fun findBooks(title: String): List<Book>
    fun findBooks(author: Author): List<Book>
    fun findBooks(year: Year): List<Book>
    fun findBooks(genre: Genre): List<Book>

    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>

    fun getBookStatus(book: Book): Status
    fun getAllBookStatuses(): Map<Book, Status>

    fun setBookStatus(book: Book, status: Status)

    fun addBook(book: Book, status: Status = Status.Available)

    fun registerUser(user: User)
    fun unregisterUser(user: User)

    fun takeBook(user: User, book: Book)
    fun returnBook(book: Book)
}

class LibraryService : LibraryInterface {
    private val books: MutableMap<Book, Status> = mutableMapOf()
    private val users: MutableList<User> = mutableListOf()

    override fun findBooks(title: String): List<Book> {
        return books.filter { it.key.title == title }.keys.toList()
    }

    override fun findBooks(author: Author): List<Book> {
        return books.filter { it.key.author == author }.keys.toList()
    }

    override fun findBooks(year: Year): List<Book> {
        return books.filter { it.key.year == year }.keys.toList()
    }

    override fun findBooks(genre: Genre): List<Book> {
        return books.filter { it.key.genre == genre }.keys.toList()
    }

    override fun getAllBooks(): List<Book> {
        return books.keys.toList()
    }

    override fun getAllAvailableBooks(): List<Book> {
        return books.filter { it.value == Status.Available }.keys.toList()
    }

    override fun getBookStatus(book: Book): Status {
        val status = books[book]
        return if (status != null) status else throw NullPointerException("Expression 'books[book]' must not be null")
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        return books
    }

    override fun setBookStatus(book: Book, status: Status) {
        books[book] = status
    }

    override fun addBook(book: Book, status: Status) {
        books[book] = status
    }

    override fun registerUser(user: User){
        users.add(user)
    }

    override fun unregisterUser(user: User) {
        if (!users.contains(user))
            throw IllegalArgumentException("User doesn't exist!")

        for (book in books.keys) {
            if (books[book] == Status.UsedBy(user)) {
                books[book] = Status.Available
            }
        }
        users.remove(user)
    }

    override fun takeBook(user: User, book: Book){
        if (!users.contains(user))
            throw IllegalArgumentException("User doesn't exist!")
        if (!books.containsKey(book))
            throw IllegalArgumentException("Book doesn't exist!")
        if (books.filter { it.value == Status.UsedBy(user) }.size < 3) {
            books[book] = Status.UsedBy(user)
            return
        }
        throw IllegalArgumentException("User cannot store more than 3 books!")
    }

    override fun returnBook(returnedBook: Book) {
        if (!books.containsKey(returnedBook))
            throw IllegalArgumentException("Book doesn't exist!")
        for (book in books.keys) {
            if (book == returnedBook) {
                books[book] = Status.Available
                return
            }
        }
    }
}