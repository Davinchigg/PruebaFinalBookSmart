class LibroDigital(
    titulo: String,
    autor: String,
    precioBase: Double,
    diasPrestamo: Int,
    val drm: Boolean = false
) : Libro(titulo, autor, precioBase, diasPrestamo) {

    override fun descripcion(): String {
        val drmText = if (drm) " (DRM)" else ""
        return super.descripcion() + drmText
    }

    // Ejemplo: si tiene DRM, agregamos un cargo pequeño
    override fun costoFinal(): Double = if (drm) precioBase + 500.0 else precioBase
}
