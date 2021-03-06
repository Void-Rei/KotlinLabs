package eltech.kotlin.lab3

import kotlin.math.*
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

class Circle(val radius: Double) : Shape {
    init {
        if (radius <= 0) throw IllegalArgumentException("Incorrect circle radius $radius. It should be more than 0!")
    }

    override fun calcArea(): Double {
        return Math.PI * radius.pow(2)

    }

    override fun calcPerimeter(): Double {
        return Math.PI * radius * 2
    }

    override fun toString (): String {
        return "Circle: r = $radius"
    }
}

class Square(val sideA: Double) : Shape {
    init {
        if (sideA <= 0) throw IllegalArgumentException("Incorrect square side $sideA. It should be more than 0!")
    }

    override fun calcArea(): Double {
        return sideA.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 4 * sideA
    }

    override fun toString (): String {
        return "Square: side = $sideA"
    }
}

class Rectangle(val sideA: Double, val sideB: Double) : Shape {
    init {
        if (sideA <= 0 && sideB < 0) throw IllegalArgumentException("Incorrect rectangle sides $sideA and $sideB. They should be more than 0!")
        if (sideA <= 0) throw IllegalArgumentException("Incorrect rectangle side $sideA. It should be more than 0!")
        if (sideB <= 0) throw IllegalArgumentException("Incorrect rectangle side $sideB. It should be more than 0!")
    }

    override fun calcArea(): Double {
        return sideA * sideB
    }

    override fun calcPerimeter(): Double {
        return (sideA + sideB) * 2
    }

    override fun toString (): String {
        return "Rectangle: side A = $sideA, side B = $sideB"
    }
}

class Triangle(val sideA: Double, val sideB: Double, val sideC: Double) : Shape {
    init {
        if (sideA <= 0) throw IllegalArgumentException("Incorrect triangle side $sideA. It should be more than 0!")
        if (sideB <= 0) throw IllegalArgumentException("Incorrect triangle side $sideB. It should be more than 0!")
        if (sideC <= 0) throw IllegalArgumentException("Incorrect triangle side $sideC. It should be more than 0!")
        if ((sideA + sideB <= sideC) || (sideB + sideC <= sideA) || (sideC + sideA <= sideB))
            throw IllegalArgumentException("The necessary and sufficient condition for the existence of a triangle is not fulfilled")
    }

    override fun calcArea(): Double {
        val halfPerimeter: Double = calcPerimeter() / 2
        return sqrt(halfPerimeter * (halfPerimeter - sideA) * (halfPerimeter - sideB) * (halfPerimeter - sideC))
    }

    override fun calcPerimeter(): Double {
        return sideA + sideB + sideC
    }

    override fun toString (): String {
        return "Triangle: side A = $sideA, side B = $sideB, side C = $sideC"
    }
}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(sideA: Double): Square
    fun createRectangle(sideA: Double, sideB: Double): Rectangle
    fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle

    fun createRandomCircle(): Circle
    fun createRandomSquare(): Square
    fun createRandomRectangle(): Rectangle
    fun createRandomTriangle(): Triangle

    fun createRandomShape(): Shape
}

class ShapeFactoryImpl : ShapeFactory {
    override fun createCircle(radius: Double): Circle {
        return Circle(radius)
    }

    override fun createSquare(sideA: Double): Square {
        return Square(sideA)
    }

    override fun createRectangle(sideA: Double, sideB: Double): Rectangle {
        return Rectangle(sideA, sideB)
    }

    override fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle {
        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomCircle(): Circle {
        val radius = Random.nextDouble(0.1, 1000.0)
        return Circle(radius)
    }

    override fun createRandomSquare(): Square {
        val a = Random.nextDouble(0.1, 1000.0)
        return Square(a)
    }

    override fun createRandomRectangle(): Rectangle {
        val a = Random.nextDouble(0.1, 1000.0)
        val b = Random.nextDouble(0.1, 1000.0)
        return Rectangle(a, b)
    }

    override fun createRandomTriangle(): Triangle {
        val a = Random.nextDouble(0.1, 1000.0)
        val b = Random.nextDouble(0.1, 1000.0)
        val c = Random.nextDouble(abs(a - b) + 0.1, a + b - 0.1)
        return Triangle(a, b, c)
    }

    override fun createRandomShape(): Shape {
        return when (Random.nextInt(4)) {
            0 -> createRandomCircle()
            1 -> createRandomSquare()
            2 -> createRandomRectangle()
            3 -> createRandomTriangle()
            else -> createRandomCircle()
        }
    }
}
