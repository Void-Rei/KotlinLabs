package eltech.kotlin.lab4

fun main() {

}

class Matrix(newValues: MutableList<MutableList<Int>>) {
    var matrix = newValues
        set(setValues) {
            field = setValues
            rows = setValues.lastIndex
            lines = setValues[0].lastIndex
        }

    private var rows = newValues.lastIndex
    private var lines = newValues[0].lastIndex

    operator fun plus(other: Matrix): Matrix {
        TODO("Not yet implemented")
    }

    operator fun plusAssign(other: Matrix) {
        TODO("Not yet implemented")
    }

    operator fun times(scalar: Double) {
        TODO("Not yet implemented")
    }

    operator fun timesAssign(scalar: Double) {
        TODO("Not yet implemented")
    }

    operator fun set(i: Int, j: Int, value: Double) {
        TODO("Not yet implemented")
    }

    operator fun get(i: Int, j: Int): Double {
        TODO("Not yet implemented")
    }

    operator fun unaryMinus(): Matrix {
        TODO("Not yet implemented")
    }

    operator fun unaryPlus(): Matrix {
        return this
    }
    // ...
}
