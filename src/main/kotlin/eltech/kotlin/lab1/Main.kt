package eltech.kotlin.lab1

enum class Alignment {
    LEFT,
    RIGHT,
    CENTER,
    WIDTH
}

class Text(newText: MutableList<String>) {
    var linesList = mutableListOf<String>()
        set(setText) {
            field = setText
            numberOfLines = setText.lastIndex
        }
    private var numberOfLines = newText.lastIndex
    var alignment = Alignment.LEFT

    private fun alignLeft(lineLength: Int) {
        val iterator = linesList.listIterator()
        var currentString: String
        var extraString: String? = null
        var currentChar: Char?

        while (iterator.hasNext())
        {
            currentString = if (extraString != null) extraString + iterator.next()
            else iterator.next()
            currentChar = currentString.getOrNull(lineLength)
            if ((currentChar != null) && currentChar.isWhitespace())
                extraString = currentString.substring(lineLength + 1).trim()
            else if((currentChar != null) && (currentChar.isLetterOrDigit() || currentChar.toString().matches("\\p{Punct}".toRegex()))) {
                for (i in lineLength downTo 1)
                {
                    if (currentChar.isWhitespace())
                        extraString = currentString.substring(i).trim()
                }
            }
            else extraString = null
        }
    }

    private fun alignRight(lineLength: Int) {

    }

    private fun alignCenter(lineLength: Int) {

    }

    private fun alignWidth(lineLength: Int) {

    }

    fun alignText(lineLength: Int) {
        when (alignment) {
            Alignment.LEFT -> alignLeft(lineLength)
            Alignment.RIGHT -> alignRight(lineLength)
            Alignment.CENTER -> alignCenter(lineLength)
            Alignment.WIDTH -> alignWidth(lineLength)
        }
    }
}

fun main() {

    val text = Text(mutableListOf("This is just a test text.",
    "Yes, kinda short.",
        "For now."))


    text.alignment = Alignment.LEFT
    val lineLength = 5

    text.alignText(lineLength)

    for (line in text.linesList)
    {
        println(line)
    }

}