open class Libro(
    val titulo: String,
    val autor: String,
    val precioBase: Double,
    val diasPrestamo: Int
) {
    init {
        require(precioBase >= 0.0) { "El precio no puede ser negativo" }
        require(diasPrestamo >= 0) { "Los días de préstamo no pueden ser negativos" }
    }

    open fun costoFinal(): Double = precioBase

    open fun descripcion(): String =
        "$titulo - $autor (${diasPrestamo} días) Precio: $precioBase"

    open fun esPrestable(): Boolean = true
}
