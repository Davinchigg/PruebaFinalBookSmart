//Clase que representa un libro fisico y hereda de libro
class LibroFisico(
    titulo: String,
    autor: String,
    precioBase: Double,
    diasPrestamo: Int,
    val esReferencia: Boolean = false
) : Libro(titulo, autor, precioBase, diasPrestamo) {

    override fun descripcion(): String {
        val ref = if (esReferencia) " (Referencia - NO prestable)" else ""
        return super.descripcion() + ref
    }

    override fun esPrestable(): Boolean = !esReferencia

    override fun costoFinal(): Double = precioBase
}
