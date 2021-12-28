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

    fun getAllSorted(comparator: Comparator<in T>): List<T> {
        val sortedShapes = mutableListOf<T>()
        sortedShapes.addAll(0, allShapes)

        return sortedShapes.sortedWith(comparator)
    }
}

sealed class ShapeComparators {
    object DescendingPerimeterComparator : ShapeComparators(), Comparator<Shape> {
        override fun compare(a: Shape, b: Shape): Int {
            return if (a.calcPerimeter() < b.calcPerimeter()) 1
            else if (a.calcPerimeter() == b.calcPerimeter()) 0
            else -1
        }
    }

    object AscendingPerimeterComparator : ShapeComparators(), Comparator<Shape> {
        override fun compare(a: Shape, b: Shape): Int {
            return if (a.calcPerimeter() > b.calcPerimeter()) 1
            else if (a.calcPerimeter() == b.calcPerimeter()) 0
            else -1

        }
    }

    object DescendingSurfaceComparator : ShapeComparators(), Comparator<Shape> {
        override fun compare(a: Shape, b: Shape): Int {
            return if (a.calcArea() < b.calcArea()) 1
            else if (a.calcArea() == b.calcArea()) 0
            else -1
        }
    }

    object AscendingSurfaceComparator : ShapeComparators(), Comparator<Shape> {
        override fun compare(a: Shape, b: Shape): Int {
            return if (a.calcArea() > b.calcArea()) 1
            else if (a.calcArea() == b.calcArea()) 0
            else -1
        }
    }

    object DescendingRadiusComparator : ShapeComparators(), Comparator<Circle> {
        override fun compare(a: Circle, b: Circle): Int {
            return if (a.radius < b.radius) 1
                else if (a.radius == b.radius) 0
                else -1
        }
    }

    object AscendingRadiusComparator : ShapeComparators(), Comparator<Circle> {
        override fun compare(a: Circle, b: Circle): Int {
            return if (a.radius > b.radius) 1
                else if (a.radius == b.radius) 0
                else -1
        }
    }
}
