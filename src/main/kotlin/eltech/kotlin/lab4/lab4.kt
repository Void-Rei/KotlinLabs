package eltech.kotlin.lab4

class Matrix(newValues: List<List<Double>>) {
    private var matrix: MutableList<MutableList<Double>>

    init {
        if (!sizeCheck(newValues)) throw IllegalArgumentException("Matrix rows must be of the same size!")

        matrix = MutableList(newValues.size) { MutableList(newValues[0].size) { 0.0 } }
        for (i in 0 until lines) {
            for (j in 0 until rows) {
                matrix[i][j] = newValues[i][j]
            }
        }

    }

    private fun sizeCheck(newList: List<List<Double>>): Boolean {
        val expectedSize = newList.lastIndex
        for (i in 1..newList.lastIndex) {
            if (newList[i].lastIndex != expectedSize) return false
        }
        return true
    }

    val lines get() = matrix.size
    val rows get() = matrix[0].size

    operator fun plusAssign(other: Matrix) {
        if (lines != other.lines || rows != other.rows) throw IllegalArgumentException("Matrices must be of the same dimension!")
        for (i in 0 until lines) {
            for (j in 0 until rows) {
                matrix[i][j] += other.matrix[i][j]
            }
        }
    }

    operator fun plus(other: Matrix): Matrix {
        if (lines != other.lines || rows != other.rows) throw IllegalArgumentException("Matrices must be of the same dimension!")
        val answer = MutableList(lines) { MutableList(rows) { 0.0 } }
        for (i in 0 until lines) {
            for (j in 0 until rows) {
                answer[i][j] = matrix[i][j] + other.matrix[i][j]
            }
        }
        return Matrix(answer)
    }

    operator fun minusAssign(other: Matrix) {
        if (lines != other.lines || rows != other.rows) throw IllegalArgumentException("Matrices must be of the same dimension!")
        for (i in 0 until lines) {
            for (j in 0 until rows) {
                matrix[i][j] -= other.matrix[i][j]
            }
        }
    }

    operator fun minus(other: Matrix): Matrix {
        if (lines != other.lines || rows != other.rows) throw IllegalArgumentException("Matrices must be of the same dimension!")
        val answer = MutableList(lines) { MutableList(rows) { 0.0 } }
        for (i in 0 until lines) {
            for (j in 0 until rows) {
                answer[i][j] = matrix[i][j] - other.matrix[i][j]
            }
        }
        return Matrix(answer)
    }

    operator fun times(scalar: Double): Matrix {
        val answer = MutableList(lines) { MutableList(rows) { 0.0 } }
        for (i in 0 until lines) {
            for (j in 0 until rows) {
                answer[i][j] = matrix[i][j] * scalar
            }
        }
        return Matrix(answer)
    }

    operator fun times(other: Matrix): Matrix {
        if (rows != other.lines) throw IllegalArgumentException("The number of rows of the first matrix must be equal to the number of columns of the second matrix!")
        val answer = MutableList(lines) { MutableList<Double>(other.rows) { 0.0 } }
        for (i in 0 until lines) {
            for (j in 0..other.rows) {
                for (k in 0 until rows) {
                    answer[i][j] += matrix[i][k] * other.matrix[k][j]
                }
            }
        }
        return Matrix(answer)
    }

    operator fun timesAssign(other: Matrix) {
        if (rows != other.lines) throw IllegalArgumentException("The number of rows of the first matrix must be equal to the number of columns of the second matrix!")
        val answer = MutableList(lines) { MutableList<Double>(other.rows) { 0.0 } }
        for (i in 0 until lines) {
            for (j in 0..other.rows) {
                for (k in 0 until rows) {
                    answer[i][j] += matrix[i][k] * other.matrix[k][j]
                }
            }
        }
        matrix = answer
    }

    operator fun timesAssign(scalar: Double) {
        for (i in 0 until lines) {
            for (j in 0 until rows) {
                matrix[i][j] *= scalar
            }
        }
    }

    operator fun div(scalar: Double): Matrix {
        val answer = MutableList(lines) { MutableList(rows) { 0.0 } }
        for (i in 0 until lines) {
            for (j in 0 until rows) {
                answer[i][j] = matrix[i][j] / scalar
            }
        }
        return Matrix(answer)
    }

    operator fun divAssign(scalar: Double) {
        for (i in 0 until lines) {
            for (j in 0 until rows) {
                matrix[i][j] /= scalar
            }
        }
    }

    private fun wrongIndexCheck(i: Int, j: Int) {
        if (j > rows) throw IndexOutOfBoundsException("Index j should be less than rows amount!")
        if (i > lines) throw IndexOutOfBoundsException("Index i should be less than lines amount!")
        if (j < 0) throw IndexOutOfBoundsException("Index j should be more than 0!")
        if (i < 0) throw IndexOutOfBoundsException("Index i should be more than 0!")
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
        val answer = MutableList(lines) { MutableList(rows) { 0.0 } }
        for (i in 0 until lines) {
            for (j in 0 until rows) {
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

        for (i in 0 until lines) {
            for (j in 0 until rows) {
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

        for (line in 0 until lines) {
            for (row in 0 until rows) {
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
