package eltech.kotlin.lab6
import eltech.kotlin.lab3.*

class ShapeCollector<T /* ??? */ > {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
        /* ??? */
    }
    fun addAll(new: /* ??? */ T) {
        /* ??? */
    }

    fun getAll(): List<T> {
        return allShapes
    }
    fun getAllSorted(comparator: /* ??? */ T): List<T> {
        /* ??? */
        return allShapes
    }
}
