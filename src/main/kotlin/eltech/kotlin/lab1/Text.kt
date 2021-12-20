package eltech.kotlin.lab1

class Text(newText: MutableList<String>) {
    var linesList = newText
        set(setText) {
            field = setText
            numberOfLines = setText.lastIndex
        }
    private var numberOfLines = newText.lastIndex

    fun alignLeft(lineLength: Int) {
        var i = 0
        var currentString: String
        var extraString = ""
        var currentChar: Char?

        while (i <= linesList.lastIndex) {
            currentString = if (extraString.isNotBlank()) extraString + " " + linesList[i]
            else linesList[i]
            currentString = fullTrim(currentString)
            currentChar = currentString.getOrNull(lineLength - 1)
            if ((currentChar != null) && currentChar.isWhitespace()) {
                extraString = currentString.substring(lineLength)
                currentString = currentString.dropLast(currentString.length - lineLength + 1)
            }
            else if ((currentChar != null) && (currentChar.isLetterOrDigit() || currentChar.toString()
                    .matches("\\p{Punct}".toRegex()))
            ) {
                for (j in (lineLength - 1) downTo 0) {
                    currentChar = currentString[j]
                    if (currentChar.isWhitespace()) {
                        extraString = currentString.substring(j)
                        currentString = currentString.dropLast(currentString.length - j)
                        break
                    }
                    else if (j == 0) {                     // case when line length is less than first word in line
                        extraString = currentString.substring(lineLength) // cropping by the end of this word
                        currentString = currentString.dropLast(currentString.length - lineLength)
                        break
                    }
                }
            }
            else extraString = ""

            linesList[i] = currentString
            if ((i == linesList.lastIndex) && extraString.isNotBlank()) {
                linesList.add("")
            }
            i++
        }
    }

    private fun findWordEnd (currentString: String): Int {
        var currentChar: Char
        for (i in 0..currentString.lastIndex) {
            currentChar = currentString[i]
            if (currentChar.isWhitespace()) {
                return i
            }
        }
        return currentString.lastIndex
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
            else if ((currentChar.isLetterOrDigit() || currentChar.toString()
                    .matches("\\p{Punct}".toRegex())) && wsFlag
            ) {
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
}
