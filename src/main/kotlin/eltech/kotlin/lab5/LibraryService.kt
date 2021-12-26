package eltech.kotlin.lab5

import java.time.Year
import kotlin.random.Random

class Book (
    val title: String,
    val author: Author,
    val genre: Genre,
    val year: Year,
        ) {
    override fun toString(): String {
        return " Title: $title\nAuthor: $author\nGenre: $genre\nYear:$year)"
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
class User (
    val firstName: String,
    val surname: String,
    val id: Int = Random.nextInt()
        ) {
    override fun toString(): String {
        return "User: $firstName $surname | $id)"
    }
}

enum class Genre {

}

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()
}

interface LibraryService {
    fun findBooks(substring: String): List<Book>
    fun findBooks(author: Author): List<Book>
    fun findBooks(year: /*Year*/Int): List<Book>
    fun findBooks(genre: Genre)

    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>

    fun getBookStatus(book: Book): Status
    fun getAllBookStatuses(): Map<Book, Status>

    fun setBookStatus(book: Book, status: Status)

    fun addBook(book: Book, status: Status = Status.Available)

    fun registerUser(/* parameters */)
    fun unregisterUser(user: User)

    fun takeBook(user: User, book: Book)
    fun returnBook(book: Book)
}
