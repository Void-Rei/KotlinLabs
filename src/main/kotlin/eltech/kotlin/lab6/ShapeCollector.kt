package eltech.kotlin.lab6

import eltech.kotlin.lab3.*

class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
        allShapes.add(new)
    }

    fun addAll(new: Collection<T>) {
        for (shape in new) {
            this.add(shape)
        }
    }

    fun getAll(): List<T> {
        return allShapes
    }

    fun getAllSorted(comparator: ShapeComparators): List<T> {
        val sortedShapes = mutableListOf<T>()
        sortedShapes.addAll(0,allShapes)

        var previous: T
        var current: T
        var sorted = false

        while (!sorted) {
            sorted = true
            for (i in 1 until sortedShapes.size) {
                previous = sortedShapes[i - 1]
                current = sortedShapes[i]
                if (!comparator.compare(previous, current)) {
                    sortedShapes[i] = previous
                    sortedShapes[i - 1] = current
                    sorted = false
                }
            }
        }
        return sortedShapes
    }
}

interface Comparator {
    fun <T : Shape> compare(a: T, b: T): Boolean
}

sealed class ShapeComparators : Comparator {
    object DescendingPerimeterComparator : ShapeComparators() {
        override fun <T : Shape> compare(a: T, b: T): Boolean {
            return a.calcPerimeter() > b.calcPerimeter()
        }
    }

    object AscendingPerimeterComparator : ShapeComparators() {
        override fun <T : Shape> compare(a: T, b: T): Boolean {
            return a.calcPerimeter() < b.calcPerimeter()
        }
    }

    object DescendingSurfaceComparator : ShapeComparators() {
        override fun <T : Shape> compare(a: T, b: T): Boolean {
            return a.calcArea() > b.calcArea()
        }
    }

    object AscendingSurfaceComparator : ShapeComparators() {
        override fun <T : Shape> compare(a: T, b: T): Boolean {
            return a.calcArea() < b.calcArea()
        }
    }

    object DescendingRadiusComparator : ShapeComparators() {
        override fun <T : Shape> compare(a: T, b: T): Boolean {
            if ((a !is Circle) || (b !is Circle)) throw IllegalArgumentException(
                "This comparator is on;y for circles," +
                        "yet a non-circle received"
            )
            return a.radius > b.radius
        }
    }

    object AscendingRadiusComparator : ShapeComparators() {
        override fun <T : Shape> compare(a: T, b: T): Boolean {
            if ((a !is Circle) || (b !is Circle)) throw IllegalArgumentException(
                "This comparator is on;y for circles," +
                        "yet a non-circle received"
            )
            return a.radius < b.radius
        }
    }
}
