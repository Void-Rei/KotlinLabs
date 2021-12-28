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
                if (comparator.compare(previous, current) == 0) {
                    sortedShapes[i] = previous
                    sortedShapes[i - 1] = current
                    sorted = false
                }
            }
        }
        return sortedShapes
    }
}

sealed class ShapeComparators : Comparator<Shape> {
    object DescendingPerimeterComparator : ShapeComparators() {
        override fun compare(a: Shape, b: Shape): Int {
            return if (a.calcPerimeter() >= b.calcPerimeter()) 1
            else 0
        }
    }

    object AscendingPerimeterComparator : ShapeComparators() {
        override fun compare(a: Shape, b: Shape): Int {
            return if (a.calcPerimeter() <= b.calcPerimeter()) 1
            else 0

        }
    }

    object DescendingSurfaceComparator : ShapeComparators() {
        override fun compare(a: Shape, b: Shape): Int {
            return if (a.calcArea() >= b.calcArea()) 1
            else 0
        }
    }

    object AscendingSurfaceComparator : ShapeComparators() {
        override fun compare(a: Shape, b: Shape): Int {
            return if (a.calcArea() <= b.calcArea()) 1
            else 0
        }
    }

    object DescendingRadiusComparator : ShapeComparators() {
        override fun compare(a: Shape, b: Shape): Int {
            if ((a !is Circle) || (b !is Circle)) throw IllegalArgumentException(
                "This comparator is only for circles," +
                        "yet a non-circle received"
            )
            return if (a.radius > b.radius) 1
            else 0
        }
    }

    object AscendingRadiusComparator : ShapeComparators() {
        override fun compare(a: Shape, b: Shape): Int {
            if ((a !is Circle) || (b !is Circle)) throw IllegalArgumentException(
                "This comparator is only for circles," +
                        "yet a non-circle received"
            )
            return if (a.radius < b.radius) 1
            else 0
        }
    }
}
