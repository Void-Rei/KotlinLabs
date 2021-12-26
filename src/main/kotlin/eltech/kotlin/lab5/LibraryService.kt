package eltech.kotlin.lab5

import java.time.Year
import kotlin.random.Random

class Book(
    val title: String,
    val author: Author,
    val genre: Genre,
    val year: Year,
    var status: Status
) {
    override fun toString(): String {
        return "Title: $title\nAuthor: $author\nGenre: $genre\nYear:$year)"
    }
}

class Author(
    val firstName: String,
    val surname: String,
    val id: Int = Random.nextInt()
) {
    override fun toString(): String {
        return "Author: $firstName $surname | $id)"
    }
}

class User(
    val firstName: String,
    val surname: String,
    val id: Int = Random.nextInt()
) {
    override fun toString(): String {
        return "User: $firstName $surname | $id)"
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
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()
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
    private val books: MutableList<Book> = mutableListOf()
    private val users: MutableList<User> = mutableListOf()

    override fun findBooks(title: String): List<Book> {
        return books.filter { it.title == title }
    }

    override fun findBooks(author: Author): List<Book> {
        return books.filter { it.author == author }
    }

    override fun findBooks(year: Year): List<Book> {
        return books.filter { it.year == year }
    }

    override fun findBooks(genre: Genre): List<Book> {
        return books.filter { it.genre == genre }
    }

    override fun getAllBooks(): List<Book> {
        return books
    }

    override fun getAllAvailableBooks(): List<Book> {
        return books.filter { it.status == Status.Available }
    }

    override fun getBookStatus(book: Book): Status {
        return book.status
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        val map: MutableMap<Book, Status> = mutableMapOf()

        for (book in books) {
            map[book] = book.status
        }
        return map
    }

    override fun setBookStatus(book: Book, status: Status) {
        book.status = status
    }

    override fun addBook(book: Book, status: Status) {
        book.status = status
        books.add(book)
    }

    override fun registerUser(user: User){
        users.add(user)
    }

    override fun unregisterUser(user: User) {
        if (!users.contains(user))
            throw IllegalArgumentException("User doesn't exist!")
        for (i in 0..books.size) {
            if (books[i].status == Status.UsedBy(user)) {
                books[i].status = Status.Available
            }
        }
        users.remove(user)
    }

    override fun takeBook(user: User, book: Book){
        if (!users.contains(user))
            throw IllegalArgumentException("User doesn't exist!")
        if (!books.contains(book))
            throw IllegalArgumentException("Book doesn't exist!")
        if (books.filter { it.status == Status.UsedBy(user) }.size < 3) {
            book.status = Status.UsedBy(user)
            return
        }
        throw IllegalArgumentException("User cannot store more than 3 books!")
    }

    override fun returnBook(book: Book) {
        if (!books.contains(book))
            throw IllegalArgumentException("Book doesn't exist!")
        for (i in 0..books.size) {
            if (books[i] == book) {
                books[i].status = Status.Available
                return
            }
        }
    }
}