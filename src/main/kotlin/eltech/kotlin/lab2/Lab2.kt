package eltech.kotlin.lab2

import java.util.ArrayDeque
import kotlin.math.pow

val operator = arrayOf('+', '-', '*', '/', '^', '_')
// Common used operators and unary minus ("negative value")
// Unary minus has different symbol from binary to distinguish in operator stack
// as well as the highest priority to invert the number before action of any other operator

const val Lbracket = '('
const val Rbracket = ')'

fun postfixCalc(inFormula: String): Int {
    val postFormula = infixToPostfix(inFormula)
    val numStack = ArrayDeque<Int>() // operands/results of calculations stored here
    var currentNumber = "" // we collect numbers by their digits in string then convert to Int

    for (symbol in postFormula) {
        when {
            symbol.isDigit() -> currentNumber += symbol // collect digit to number
            (symbol in operator) -> {
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
            else -> throw IllegalArgumentException(
                "Unexpected symbol found: $symbol." +
                        "\nCannot process given formula"
            )
        }
    }
    return numStack.removeLast() // last number -> total result
}

private fun doMath(numStack: ArrayDeque<Int>, op: Char) {

    when (op) {
        operator[0] -> numStack.add(numStack.removeLast() + numStack.removeLast())
        operator[1] -> numStack.add(numStack.removeLast() - numStack.removeLast())
        operator[2] -> numStack.add(numStack.removeLast() * numStack.removeLast())
        operator[3] -> {
            val denominator = numStack.removeLast() // we are extracting numbers in reverse
            val numerator = numStack.removeLast() // bc of the stack structure
            numStack.add(numerator / denominator)
        }
        operator[4] -> {
            val power = numStack.removeLast() // we are extracting numbers in reverse
            val base = numStack.removeLast().toDouble() // bc of the stack structure
            numStack.add(base.pow(power).toInt())
        }
        operator[5] -> numStack.add(0 - numStack.removeLast())
        else -> throw IllegalArgumentException(
            "Unexpected symbol found: $op." +
                    "\nCannot process given formula"
        )
    }
}

private fun infixToPostfix(inFormula: String): String {
    val opStack = ArrayDeque<Char>() // storage for operators
    var postFormula = ""
    var previousSymbol = Lbracket

    for (symbol in inFormula) {
        when {
            symbol.isDigit() -> postFormula += symbol
            symbol == Lbracket -> opStack.add(symbol)
            symbol == Rbracket -> {
                postFormula = ' ' + bracketFlush(opStack, postFormula)
            }
            symbol in operator -> {
                postFormula = if ((symbol == '+') && opOrBracket(previousSymbol)) {
                    continue
                }
                else if ((symbol == '-') && opOrBracket(previousSymbol)) {
                    opAdd('_', opStack, postFormula)
                }
                else {
                    opAdd(symbol, opStack, postFormula)
                }
            }
            symbol == ' ' -> {
                if (previousSymbol != ' ') postFormula += ' '
            }
            else -> throw IllegalArgumentException(
                "Unexpected symbol found: $symbol." +
                        "\nCannot process given formula"
            )
        }
        previousSymbol = if ((symbol == '-') && opOrBracket(previousSymbol)) '_'
        else symbol
    }

    while (opStack.isNotEmpty()) {
        postFormula += opStack.removeLast()
    }
    return postFormula
}

private fun opOrBracket(symbol: Char): Boolean {
    return ((symbol in operator) || (symbol == Lbracket))
}

private fun opAdd(currentChar: Char, opStack: ArrayDeque<Char>, postFormula: String): String {
    val minPriority = operator.indexOf(currentChar)
    var resultFormula = postFormula + ' ' // add space to separate from previous symbol

    while (opStack.isNotEmpty() && (operator.indexOf(opStack.peekLast()) >= minPriority)) {
        resultFormula += opStack.removeLast() // adding all operators to formula with higher
    }
    opStack.add(currentChar)
    return resultFormula
}

private fun bracketFlush(opStack: ArrayDeque<Char>, postFormula: String): String {
    var tempChar = opStack.removeLast()
    var resultFormula = postFormula

    while (tempChar != Lbracket) {
        resultFormula += tempChar // adding all leftover operators inside brackets
        tempChar = opStack.removeLast()
    }
    return resultFormula
}