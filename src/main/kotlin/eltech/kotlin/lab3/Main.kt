package eltech.kotlin.lab3

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
    println("Max shape perimeter: " + shapes.maxOf { it.calcArea() })
    println("Max shape area: " + shapes.maxOf { it.calcPerimeter() })
    println("Sum of shapes perimeter: " + shapes.sumOf { it.calcArea() })
    println("Sum of shapes area: " + shapes.sumOf { it.calcPerimeter() })
}
