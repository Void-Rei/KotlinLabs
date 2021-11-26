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