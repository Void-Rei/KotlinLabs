package eltech.kotlin.lab3

import kotlin.math.*
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

class Circle (val radius: Double) : Shape {
    init {
        if (radius <= 0) throw IllegalArgumentException("Incorrect circle radius $radius. It should be more than 0!")
    }
    override fun calcArea(): Double {
        return Math.PI * radius.pow(2)

    }
    override fun calcPerimeter(): Double {
        return Math.PI * radius * 2
    }
}

class Square (val sideA: Double) : Shape {
    init {
        if (sideA <= 0) throw IllegalArgumentException("Incorrect square side $sideA. It should be more than 0!")
    }
    override fun calcArea(): Double {
        return sideA.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 4 * sideA
    }
}

class Rectangle (val sideA: Double, val sideB: Double) : Shape {
    init {
        if (sideA <= 0 && sideB < 0) throw IllegalArgumentException("Incorrect square sides $sideA and $sideB. They should be more than 0!")
        if (sideA <= 0) throw IllegalArgumentException("Incorrect square side $sideA. It should be more than 0!")
        if (sideB <= 0) throw IllegalArgumentException("Incorrect square side $sideB. It should be more than 0!")
    }
    override fun calcArea(): Double {
        return sideA * sideB
    }

    override fun calcPerimeter(): Double {
        return (sideA + sideB) * 2
    }
}

class Triangle (val sideA: Double, val sideB: Double, val sideC: Double) : Shape {
    init {
        if (sideA <= 0) throw IllegalArgumentException("Incorrect square side $sideA. It should be more than 0!")
        if (sideB <= 0) throw IllegalArgumentException("Incorrect square side $sideB. It should be more than 0!")
        if (sideC <= 0) throw IllegalArgumentException("Incorrect square side $sideC. It should be more than 0!")
        if (sideA + sideB <= sideC || sideB + sideC <= sideA || sideC + sideA <= sideB) throw IllegalArgumentException("The necessary and sufficient condition for the existence of a triangle is not fulfilled")
    }
    override fun calcArea(): Double {
        val halfPerimeter: Double = calcPerimeter() / 2
        return sqrt(halfPerimeter * (halfPerimeter - sideA) * (halfPerimeter - sideB) * (halfPerimeter - sideC))
    }

    override fun calcPerimeter(): Double {
        return sideA + sideB + sideC
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
        val radius = Random.nextDouble(0.1 ,1000.0)
        return Circle(radius)
    }

    override fun createRandomSquare(): Square {
        val a = Random.nextDouble(0.1 ,1000.0)
        return Square(a)
    }

    override fun createRandomRectangle(): Rectangle {
        val a = Random.nextDouble(0.1 ,1000.0)
        val b = Random.nextDouble(0.1 ,1000.0)
        return Rectangle(a,b)
    }

    override fun createRandomTriangle(): Triangle {
        val a = Random.nextDouble(0.1 ,1000.0)
        val b = Random.nextDouble(0.1 ,1000.0)
        val c = Random.nextDouble(0.1 ,a+b+1)
        return Triangle(a,b,c)
    }

    override fun createRandomShape(): Shape {
        return when (Random.nextInt(4)){
            0 -> createRandomCircle()
            1 -> createRandomSquare()
            2 -> createRandomRectangle()
            3 -> createRandomTriangle()
            else -> createRandomCircle()
        }
    }
}
