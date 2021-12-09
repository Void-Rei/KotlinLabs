package eltech.kotlin.lab3

import kotlin.math.*
import kotlin.random.Random

fun main()
{
    val test = ShapeFactoryImpl()
    //test.createRectangle(4.0, 5.0)
    //test.createCircle(4.0)
    val shapes = listOf (
        test.createRectangle(4.0, 5.0),
        test.createCircle(3.0),
        test.createSquare(7.0),
        test.createTriangle(10.0, 8.0, 6.0),

        test.createRandomRectangle(),
        test.createRandomCircle(),
        test.createRandomTriangle(),
        test.createRandomSquare(),
        test.createRandomShape(),

    )
    println("Max shape perimeter: " + shapes.maxOf { it -> it.calcArea() })
    println("Max shape area: " + shapes.maxOf { it -> it.calcPerimeter() })
    println("Sum of shapes perimeter: " + shapes.sumOf { it -> it.calcArea() })
    println("Sum of shapes area: " + shapes.sumOf { it -> it.calcPerimeter() })
}

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

class Square (val side_a: Double) : Shape {
    init {
        if (side_a <= 0) throw IllegalArgumentException("Incorrect square side $side_a. It should be more than 0!")
    }
    override fun calcArea(): Double {
        return side_a.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 4 * side_a
    }
}

class Rectangle (val side_a: Double, val side_b: Double) : Shape {
    init {
        if (side_a <= 0 && side_b < 0) throw IllegalArgumentException("Incorrect square sides $side_a and $side_b. They should be more than 0!")
        if (side_a <= 0) throw IllegalArgumentException("Incorrect square side $side_a. It should be more than 0!")
        if (side_b <= 0) throw IllegalArgumentException("Incorrect square side $side_b. It should be more than 0!")
    }
    override fun calcArea(): Double {
        return side_a * side_b
    }

    override fun calcPerimeter(): Double {
        return (side_a + side_b) * 2
    }
}

class Triangle (val side_a: Double, val side_b: Double, val side_c: Double) : Shape {
    init {
        if (side_a <= 0) throw IllegalArgumentException("Incorrect square side $side_a. It should be more than 0!")
        if (side_b <= 0) throw IllegalArgumentException("Incorrect square side $side_b. It should be more than 0!")
        if (side_c <= 0) throw IllegalArgumentException("Incorrect square side $side_c. It should be more than 0!")
        if (side_a + side_b <= side_c || side_b + side_c <= side_a || side_c + side_a <= side_b) throw IllegalArgumentException("The necessary and sufficient condition for the existence of a triangle is not fulfilled")
    }
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

    override fun createSquare(side_a: Double): Square {
        return Square(side_a)
    }

    override fun createRectangle(side_a: Double, side_b: Double): Rectangle {
        return Rectangle(side_a, side_b)
    }

    override fun createTriangle(side_a: Double, side_b: Double, side_c: Double): Triangle {
        return Triangle(side_a, side_b, side_c)
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
        val c = Random.nextDouble(0.1 ,1000.0)
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
