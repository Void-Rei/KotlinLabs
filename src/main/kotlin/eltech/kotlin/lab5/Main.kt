package eltech.kotlin.lab5

import java.time.Year

fun main() {
    val library = fillLibrary()

    println(library.findBooks("Темная Башня").toString() + System.lineSeparator())
    println(library.findBooks(Year.of(1863)).toString() + System.lineSeparator())
    println(library.findBooks(Genre.Detective).toString() + System.lineSeparator())
    val author = Author("Айзек", "Азимов")
    println(library.findBooks(author).toString() + System.lineSeparator())

    val allBooks = library.getAllBooks()

    for (book in allBooks) {
        println(book)
        println("-")
    }
    println(System.lineSeparator())

    val testUser = User("Владимир", "Инвокерович")

    library.registerUser(testUser)
    library.registerUser(User("Петр", "Тритонов"))
    library.registerUser(User("Олег", "Тасманов"))

    library.takeBook(testUser, library.findBooks("Темная Башня")[0])
    val allBooksStatuses = library.getAllBookStatuses()

    for (book in allBooksStatuses) {
        println(book)
        println("-")
    }
    println(System.lineSeparator())

    var allAvailableBooks = library.getAllAvailableBooks()

    for (book in allAvailableBooks) {
        println(book)
        println("-")
    }
    println(System.lineSeparator())

    println(
        "Темная Башня" + library.getBookStatus(library.findBooks("Темная Башня")[0]).toString()
                + System.lineSeparator()
    )
    library.returnBook(library.findBooks("Темная Башня")[0])
    println(
        "Темная Башня" + library.getBookStatus(library.findBooks("Темная Башня")[0]).toString()
                + System.lineSeparator()
    )

    library.takeBook(testUser, library.findBooks("Война и мир")[0])
    library.takeBook(testUser, library.findBooks("Я робот")[0])
    library.unregisterUser(testUser)
    library.setBookStatus(library.findBooks("Человек тени")[0], Status.Available)

    allAvailableBooks = library.getAllAvailableBooks()

    for (book in allAvailableBooks) {
        println(book)
        println("-")
    }
    println(System.lineSeparator())
}

fun fillLibrary(): LibraryService {
    val library = LibraryService()
    library.addBook(
        Book(
            "Темная Башня",
            Author("Стивен", "Кинг"),
            Genre.Fantasy,
            Year.of(2004)
        )
    )

    library.addBook(
        Book(
            "Я робот",
            Author("Айзек", "Азимов"),
            Genre.ScienceFiction,
            Year.of(1950)
        )
    )
    library.addBook(
        Book(
            "Человек тени",
            Author("Джефф", "Нун"),
            Genre.Detective,
            Year.of(2017)
        ), Status.ComingSoon
    )

    library.addBook(
        Book(
            "Сказка о мёртвой царевне и о семи богатырях",
            Author("Александр", "Пушкин"),
            Genre.Poetry,
            Year.of(1834)
        ), Status.Restoration
    )

    library.addBook(
        Book(
            "Война и мир",
            Author("Лев", "Толстой"),
            Genre.Classic,
            Year.of(1863)
        )
    )

    return library
}