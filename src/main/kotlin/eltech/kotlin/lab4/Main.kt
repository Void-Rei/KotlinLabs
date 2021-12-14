package eltech.kotlin.lab4

fun main() {
    val matrix1 = Matrix (mutableListOf(mutableListOf(1.0,2.0,3.0), mutableListOf(4.0,5.0,6.0)))    // creating first matrix
    println(matrix1[0,1])   // getting the value 2
    matrix1[1,0] = 34.0     // setting a new value for the matrix element
    println(matrix1[1,0])   // getting the changed value 34

    matrix1 *= 10.0              // multiply the matrix by 10
    println(matrix1.toString())  // output the result
    matrix1 /= 2.0               // divide the matrix by 2
    println(matrix1.toString())  // output the result

    println(matrix1 * 8.0)       // multiply by 8 without changing the matrix
    println(matrix1.toString())  // output the result
    println(matrix1 / 2.0)       // divide by 2 without changing the matrix
    println(matrix1.toString())  // output the result
    println(-matrix1)            // unary minus without changing the matrix
    println(matrix1.toString())  //output the result

    val matrix2 = Matrix (mutableListOf(mutableListOf(10.0,20.0,30.0), mutableListOf(40.0,50.0,60.0)))    // creating second matrix

    println(matrix1 + matrix2)   // addition of two matrices, without changing the matrix on the left
    println(matrix1.toString())  // make sure that the matrix on the left has not changed
    matrix1 += matrix2           // addition of two matrices, with changing the matrix on the left
    println(matrix1.toString())  // make sure that the matrix on the left has changed

    println(matrix1 - matrix2)   // subtracting two matrices, without changing the matrix on the left
    println(matrix1.toString())  // make sure that the matrix on the left has not changed
    matrix1 -= matrix2           // subtracting two matrices, with changing the matrix on the left
    println(matrix1.toString())  // make sure that the matrix on the left has changed

    var sizes = matrix1.getSizes()                                   // get sizes of matrix as Pair<Int lines, Int rows>
    println("Sizes of matrix1 are: ${sizes.first}, ${sizes.second}") // see the sizes
    sizes = matrix2.getSizes()                                       // get sizes of matrix as Pair<Int lines, Int rows>
    println("Sizes of matrix2 are: ${sizes.first}, ${sizes.second}") // see the sizes

    println("Matrix1 and matrix2 are equal: ${matrix1 == matrix2}")                              // checking matrices for equality
    val matrix3 = Matrix (mutableListOf(mutableListOf(1.0,2.0,3.0), mutableListOf(4.0,5.0,6.0))) // creating third matrix
    val matrix4 = Matrix (mutableListOf(mutableListOf(1.0,2.0,3.0), mutableListOf(4.0,5.0,6.0))) // creating fourth matrix, same as third
    println("Matrix1 and matrix2 are equal: ${matrix4 == matrix3}")                              // checking matrices for equality

}