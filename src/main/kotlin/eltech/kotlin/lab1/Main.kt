package eltech.kotlin.lab1

fun main() {

    val text = Text(
        mutableListOf(
            "   This  is  just  a  test  text.",
            "      Yes, kinda short.",
            "For    now."
        )
    )

    val lineLength = 1

    text.alignLeft(lineLength)

    for (line in text.linesList) {
        println(line)
    }

}