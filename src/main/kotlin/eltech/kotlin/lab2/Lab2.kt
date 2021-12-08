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

fun infixToPostfix(InFormula: String): String {
    val opStack = ArrayDeque<Char>() // storage for operators
    var postFormula = ""
    var previousSymbol = Lbracket

    for (symbol in InFormula) {
        when {
            symbol.isDigit() -> postFormula += symbol
            symbol == Lbracket -> opStack.add(symbol)
            symbol == Rbracket -> {
                postFormula = ' ' + bracketFlush(opStack, postFormula)
            }
            symbol in operator -> {
                postFormula = if ((symbol == '+') && opOrBracket(previousSymbol)) {
                    continue
                } else if ((symbol == '-') && opOrBracket(previousSymbol)) {
                    opAdd('_', opStack, postFormula)
                } else {
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
}