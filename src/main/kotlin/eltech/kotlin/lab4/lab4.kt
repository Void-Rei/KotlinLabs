package eltech.kotlin.lab4

class Matrix(newValues: MutableList<MutableList<Double>>) {
    private var matrix = newValues
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
        val answer = MutableList(lines + 1) { MutableList(rows + 1) { 0.0 } }
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                answer[i][j] = matrix[i][j] + other.matrix[i][j]
            }
        }
        return Matrix(answer)
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
        val answer = MutableList(lines + 1) { MutableList(rows + 1) { 0.0 } }
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                answer[i][j] = matrix[i][j] - other.matrix[i][j]
            }
        }
        return Matrix(answer)
    }

    operator fun times(scalar: Double): Matrix {
        val answer = MutableList(lines + 1) { MutableList(rows + 1) { 0.0 } }
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                answer[i][j] = matrix[i][j] * scalar
            }
        }
        return Matrix(answer)
    }

    operator fun timesAssign(scalar: Double) {
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                matrix[i][j] *= scalar
            }
        }
    }

    operator fun div(scalar: Double): Matrix {
        val answer = MutableList(lines + 1) { MutableList(rows + 1) { 0.0 } }
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                answer[i][j] = matrix[i][j] / scalar
            }
        }
        return Matrix(answer)
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
        wrongIndexCheck(line, row)
        return matrix[line][row]
    }

    operator fun unaryMinus(): Matrix {
        val answer = MutableList(lines + 1) { MutableList(rows + 1) { 0.0 } }
        for (i in 0 .. lines) {
            for (j in 0 .. rows) {
                answer[i][j] = -matrix[i][j]
            }
        }
        return Matrix(answer)
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (matrix != other.matrix) return false
        if (lines != other.lines) return false
        if (rows != other.rows) return false

        for (line in 0..lines) {
            for (row in 0..rows)
            {
                if (matrix[line][row] != other.matrix[line][row]) return false
            }
        }

        return true
    }

    override fun hashCode(): Int {
        var result = matrix.hashCode()
        result = 31 * result + lines
        result = 31 * result + rows
        return result
    }
}
