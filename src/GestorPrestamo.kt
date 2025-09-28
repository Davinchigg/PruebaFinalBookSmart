

object GestorPrestamos {

    fun inicializarCatalogo(): List<Libro> = listOf(
        LibroFisico("Estructuras de Datos", "Goodrich", 12990.0, 7, esReferencia = false),
        LibroFisico("Diccionario Enciclopédico", "Varios", 15990.0, 0, esReferencia = true),
        LibroDigital("Programación en Kotlin", "JetBrains", 9990.0, 10, drm = true),
        LibroDigital("Algoritmos Básicos", "Cormen", 11990.0, 10, drm = false)
    )

    fun mostrarCatalogo(catalogo: List<Libro>) {
        println("----- Catálogo BookSmart -----")
        catalogo.forEachIndexed { i, libro ->
            println("${i + 1}. ${libro.descripcion()}")
        }
        println("-----------------------------")
    }

    fun calcularSubtotal(seleccion: List<Libro>): Double =
        seleccion.sumOf { it.costoFinal() }

    fun aplicarDescuento(subtotal: Double, tipoUsuario: String): Double {
        val tasa = when (tipoUsuario.lowercase()) {
            "estudiante" -> 0.10
            "docente" -> 0.15
            else -> 0.0
        }
        return subtotal * tasa
    }

    fun calcularMultaPorRetraso(seleccion: List<Libro>, diasRetraso: Int): Double {
        if (diasRetraso <= 0) return 0.0
        // Multa: 1% del precio por día por libro
        return seleccion.sumOf { it.costoFinal() * 0.01 * diasRetraso }
    }

    // Simula procesamiento asíncrono (3 segundos)
      fun procesarPrestamoAsync(seleccion: List<Libro>): EstadoPrestamo {
        // Validar que no se intenten prestar referencias
        val noPrestables = seleccion.filter { !it.esPrestable() }
        if (noPrestables.isNotEmpty()) {
            val titulos = noPrestables.joinToString { it.titulo }
            return EstadoPrestamo.Error("Incluye libros no prestables: $titulos")
        }

        println("Procesando solicitud... (simulando 3 segundos)")
        Thread.sleep(3000L)
        return EstadoPrestamo.EnPrestamo
    }
}
