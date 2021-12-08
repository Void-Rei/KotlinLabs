package eltech.kotlin.lab2

import java.util.ArrayDeque

val operator = arrayOf('+', '-', '*', '/', '^', '_')
// Common used operators and unary minus ("negative value")
// Unary minus has different symbol from binary to distinguish in operator stack
// as well as the highest priority to invert the number before action of any other operator

const val Lbracket = '('
const val Rbracket = ')'

fun main() {
    println(postfixCalc(readLine()!!))
}

fun postfixCalc (InFormula: String): Int {
    val postFormula = infixToPostfix(InFormula)
    val numStack = ArrayDeque<Int>() // operands/results of calculations stored here
    var currentNumber = "" // we collect numbers by their digits in string then convert to Int

    for (symbol in postFormula) {
        when {
            symbol.isDigit() -> currentNumber += symbol // collect digit to number
            (symbol in operator) ->{
                if (currentNumber != "") {
                    numStack.add(currentNumber.toInt()) // digits in a row stopped -> new number ready
                    currentNumber = ""
                }
                doMath(numStack, symbol)
            }
            symbol == ' ' -> {
                if (currentNumber != "") {
                    numStack.add(currentNumber.toInt()) // digits in a row stopped -> new number ready
                    currentNumber = ""
                }
            }
            else -> throw IllegalArgumentException("Unexpected symbol found: $symbol." +
                    "\nCannot process given formula")
        }
    }
    return numStack.removeLast() // last number -> total result
}
