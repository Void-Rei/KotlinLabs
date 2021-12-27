package eltech.kotlin.lab6

import eltech.kotlin.lab3.Shape
import eltech.kotlin.lab3.ShapeFactoryImpl

fun main() {
    val shapeCollector = ShapeCollector<Shape>()
    val circleCollector = ShapeCollector<Shape>()
    val test = ShapeFactoryImpl()

    shapeCollector.add(test.createRandomTriangle())
    shapeCollector.add(test.createRandomRectangle())
    shapeCollector.add(test.createRandomSquare())
    shapeCollector.add(test.createRandomShape())

    var testList = listOf(
        test.createRandomCircle(),
        test.createRandomTriangle(),
        test.createRandomShape(),
        test.createRandomShape()
    )

    shapeCollector.addAll(testList)
    var allShapes = shapeCollector.getAll()
    for (shape in allShapes) {
        println(shape)
        println("-")
    }
    println(System.lineSeparator())

    allShapes = shapeCollector.getAllSorted(ShapeComparators.DescendingPerimeterComparator)
    println("Descending")
    for (shape in allShapes) {
        println(shape.calcPerimeter())
        println("-")
    }
    println(System.lineSeparator())

    allShapes = shapeCollector.getAllSorted(ShapeComparators.AscendingPerimeterComparator)
    println("Ascending")
    for (shape in allShapes) {
        println(shape.calcPerimeter())
        println("-")
    }
    println(System.lineSeparator())

    allShapes = shapeCollector.getAllSorted(ShapeComparators.DescendingSurfaceComparator)
    println("Descending")
    for (shape in allShapes) {
        println(shape.calcArea())
        println("-")
    }
    println(System.lineSeparator())

    allShapes = shapeCollector.getAllSorted(ShapeComparators.AscendingSurfaceComparator)
    println("Ascending")
    for (shape in allShapes) {
        println(shape.calcArea())
        println("-")
    }
    println(System.lineSeparator())

    testList = listOf(
        test.createRandomCircle(),
        test.createRandomCircle(),
        test.createRandomCircle(),
        test.createRandomCircle()
    )

    circleCollector.addAll(testList)
    var allCircles = circleCollector.getAll()
    for (shape in allCircles) {
        println(shape)
        println("-")
    }
    println(System.lineSeparator())

    allCircles = shapeCollector.getAllSorted(ShapeComparators.AscendingPerimeterComparator)
    println("Ascending")
    for (shape in allCircles) {
        println(shape.calcPerimeter())
        println("-")
    }
    println(System.lineSeparator())

    allCircles = shapeCollector.getAllSorted(ShapeComparators.DescendingSurfaceComparator)
    println("Descending")
    for (shape in allCircles) {
        println(shape.calcArea())
        println("-")
    }
    println(System.lineSeparator())

    allCircles = circleCollector.getAllSorted(ShapeComparators.DescendingRadiusComparator)
    println("Descending")
    for (shape in allCircles) {
        println(shape)
        println("-")
    }
    println(System.lineSeparator())

    allCircles = circleCollector.getAllSorted(ShapeComparators.AscendingRadiusComparator)
    println("Ascending")
    for (shape in allCircles) {
        println(shape)
        println("-")
    }
}