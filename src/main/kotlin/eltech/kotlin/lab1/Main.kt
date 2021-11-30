package eltech.kotlin.lab1

enum class Alignment {
    LEFT,
    RIGHT,
    CENTER,
    WIDTH
}

class Text(newText: MutableList<String>) {
    var linesList = newText
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

    private fun fullTrim(string: String): String {
        var resultString = string
        resultString = resultString.trim()
        var currentChar: Char
        var wsFlag = false
        var splitIndexL = 1
        var wsCount = 0
        var i = 0

        while (i < resultString.length) {
            currentChar = resultString[i]
            if (currentChar.isWhitespace() && !wsFlag) {
                wsFlag = true
                splitIndexL = i + 1
            }
            else if (currentChar.isWhitespace() && wsFlag) {
                wsCount++
            }
            else if ((currentChar.isLetterOrDigit() || currentChar.toString().matches("\\p{Punct}".toRegex())) && wsFlag) {
                if (wsCount != 0) {
                    resultString = resultString.substring(0, splitIndexL) + resultString.substring(i)
                    i -= wsCount
                    wsCount = 0
                }
                wsFlag = false
            }
            i++
        }
        return resultString
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

    val text = Text(mutableListOf("   This  is  just  a  test  text.",
    "      Yes, kinda short.", "For    now."))

    text.alignment = Alignment.LEFT
    val lineLength = 5

    text.alignText(lineLength)

    for (line in text.linesList)
    {
        println(line)
    }

}