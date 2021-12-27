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
    fun getAllSorted(comparator: ShapeComparators<T>): List<T> {
        val sortedShapes = allShapes

            /* ??? */

        return sortedShapes
    }
}

class ShapeComparators<T : Shape> {
    fun descendingPerimeterComparator(a: T, b: T): Pair<T, T> {
        return if (a.calcPerimeter() > b.calcPerimeter()) Pair(a, b)
        else Pair(b, a)
    }

    fun ascendingPerimeterComparator(a: T, b: T): Pair<T,T> {
        return if (a.calcPerimeter() < b.calcPerimeter()) Pair(a, b)
        else Pair(b, a)
    }

    fun descendingSurfaceComparator(a: T, b: T): Pair<T,T> {
        return if (a.calcArea() > b.calcArea()) Pair(a, b)
        else Pair(b, a)
    }

    fun ascendingSurfaceComparator(a: T, b: T): Pair<T,T> {
        return if (a.calcArea() < b.calcArea()) Pair(a, b)
        else Pair(b, a)
    }

    fun descendingRadiusComparator(a: Circle, b: Circle): Pair<Circle,Circle> {
        return if (a.radius > b.radius) Pair(a, b)
        else Pair(b, a)
    }

    fun ascendingRadiusComparator(a: Circle, b: Circle): Pair<Circle,Circle> {
        return if (a.radius < b.radius) Pair(a, b)
        else Pair(b, a)
    }
}
