package eltech.kotlin.lab3

import kotlin.math.*

fun main() {

}

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

class Circle (val radius: Double) : Shape {

    override fun calcArea(): Double {
        return Math.PI * radius.pow(2)
    }
    override fun calcPerimeter(): Double {
        return Math.PI * radius * 2
    }
}

class Square (val side_a: Double) : Shape {

    override fun calcArea(): Double {
        return side_a.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 4 * side_a
    }
}

class Rectangle (val side_a: Double, val side_b: Double) : Shape {

    override fun calcArea(): Double {
        return side_a * side_b
    }

    override fun calcPerimeter(): Double {
        return (side_a + side_b) * 2
    }
}

class Triangle (val side_a: Double, val side_b: Double, val side_c: Double) : Shape {

    override fun calcArea(): Double {
        val halfPerimeter: Double = calcPerimeter() / 2
        return sqrt(halfPerimeter * (halfPerimeter - side_a) * (halfPerimeter - side_b) * (halfPerimeter - side_c))
    }

    override fun calcPerimeter(): Double {
        return side_a + side_b + side_c
    }
}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(side_a: Double): Square
    fun createRectangle(side_a: Double, side_b: Double): Rectangle
    fun createTriangle(side_a: Double, side_b: Double, side_c: Double): Triangle

    /*
    fun createRandomCircle(): Circle
    fun createRandomSquare(): Square
    fun createRandomRectangle(): Rectangle
    fun createRandomTriangle(): Triangle

    fun createRandomShape(): Shape

     */
}

class ShapeFactoryImpl : ShapeFactory {
    override fun createCircle(radius: Double): Circle {
        return Circle(radius)
    }

    override fun createSquare(side_a: Double): Square {
        return Square(side_a)
    }

    override fun createRectangle(side_a: Double, side_b: Double): Rectangle {
        return Rectangle(side_a, side_b)
    }

    override fun createTriangle(side_a: Double, side_b: Double, side_c: Double): Triangle {
        return Triangle(side_a, side_b, side_c)
    }

}

fun main()
{
    val test = ShapeFactoryImpl()
    test.createRectangle(4.0, 5.0)
    test.createCircle(4.0)
}