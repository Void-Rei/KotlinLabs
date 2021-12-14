package eltech.kotlin.lab4

fun main() {
    
}

class Matrix(newValues: MutableList<MutableList<Double>>) {
    var matrix = newValues
        set(setValues) {
            field = setValues
            lines = setValues.lastIndex
            rows = setValues[0].lastIndex
        }

    private var lines = newValues.lastIndex
    private var rows = newValues[0].lastIndex

    fun getSizes(): Pair<Int, Int> {
        return Pair(lines, rows)
    }

    operator fun plusAssign(other: Matrix) {
        if (lines != other.lines || rows != other.rows) throw IllegalArgumentException("Matrices must be of the same dimension!")
        for (i in 0..lines) {
            for (j in 0..rows) {
                matrix[i][j] += other.matrix[i][j]
            }
        }
    }

    operator fun plus(other: Matrix): Matrix {
        if (lines != other.lines || rows != other.rows) throw IllegalArgumentException ("Matrices must be of the same dimension!")
        val ans = MutableList(lines + 1) { MutableList<Double>(rows + 1) { 0.0 } }
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                ans[i][j] = matrix[i][j] + other.matrix[i][j]
            }
        }
        return Matrix(ans)
    }

    operator fun minusAssign(other: Matrix) {
        if (lines != other.lines || rows != other.rows) throw IllegalArgumentException("Matrices must be of the same dimension!")
        for (i in 0..lines) {
            for (j in 0..rows) {
                matrix[i][j] -= other.matrix[i][j]
            }
        }
    }

    operator fun minus(other: Matrix): Matrix {
        if (lines != other.lines || rows != other.rows) throw IllegalArgumentException ("Matrices must be of the same dimension!")
        val ans = MutableList(lines + 1) { MutableList<Double>(rows + 1) { 0.0 } }
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                ans[i][j] = matrix[i][j] - other.matrix[i][j]
            }
        }
        return Matrix(ans)
    }

    operator fun times(scalar: Double): Matrix {
        val ans = MutableList(lines + 1) { MutableList<Double>(rows + 1) { 0.0 } }
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                ans[i][j] = matrix[i][j] * scalar
            }
        }
        return Matrix(ans)
    }

    operator fun timesAssign(scalar: Double) {
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                matrix[i][j] *= scalar
            }
        }
    }

    operator fun div(scalar: Double): Matrix {
        val ans = MutableList(lines + 1) { MutableList<Double>(rows + 1) { 0.0 } }
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                ans[i][j] = matrix[i][j] / scalar
            }
        }
        return Matrix(ans)
    }

    operator fun divAssign(scalar: Double) {
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                matrix[i][j] /= scalar
            }
        }
    }

    private fun wrongIndexCheck(i: Int, j: Int)
    {
        if (j > rows) throw IndexOutOfBoundsException ("Index j should be less than rows amount!")
        if (i > lines) throw IndexOutOfBoundsException ("Index i should be less than lines amount!")
        if (j < 0) throw IndexOutOfBoundsException ("Index j should be more than 0!")
        if (i < 0) throw IndexOutOfBoundsException ("Index i should be more than 0!")
    }

    operator fun set(line: Int, row: Int, value: Double) {
        wrongIndexCheck(line, row)
        matrix[line][row] = value
    }

    operator fun get(line: Int, row: Int): Double {
        return matrix[line][row]
    }

    operator fun unaryMinus(): Matrix {
        val ans = MutableList(lines + 1) { MutableList<Double>(rows + 1) { 0.0 } }
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                ans[i][j] = -matrix[i][j]
            }
        }
        return Matrix(ans)
    }

    operator fun unaryPlus(): Matrix {
        return this
    }

    override fun toString(): String {
        var resultString = ""

        for (i in 0..lines) {
            for (j in 0..rows) {
                resultString += matrix[i][j].toString() + ' '
            }
            resultString += System.lineSeparator()
         }
        return resultString
    }
}
