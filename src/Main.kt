fun main() {
    println("📚 Bienvenido a BookSmart 📚")
    val catalogo = GestorPrestamos.inicializarCatalogo()
    GestorPrestamos.mostrarCatalogo(catalogo)

    println("Seleccione los libros que desea prestar (ejemplo: 1,3):")
    val indicesSeleccionados = readln().split(",").mapNotNull {
        it.trim().toIntOrNull()?.minus(1)
    }

    val seleccion = indicesSeleccionados.mapNotNull { index ->
        catalogo.getOrNull(index)
    }

    if (seleccion.isEmpty()) {
        println("No se seleccionaron libros válidos.")
        return
    }

    println("Ingrese su tipo de usuario (estudiante, docente, externo):")
    val tipoUsuario = readln().trim()

    println("Ingrese los días de retraso (si no hay, escriba 0):")
    val diasRetraso = readln().toIntOrNull() ?: 0

    val estado = GestorPrestamos.procesarPrestamoAsync(seleccion)

    when (estado) {
        is EstadoPrestamo.EnPrestamo -> {
            val subtotal = GestorPrestamos.calcularSubtotal(seleccion)
            val descuento = GestorPrestamos.aplicarDescuento(subtotal, tipoUsuario)
            val multa = GestorPrestamos.calcularMultaPorRetraso(seleccion, diasRetraso)
            val total = subtotal - descuento + multa

            println("\n✅ Préstamo exitoso. Resumen:")
            seleccion.forEach { println(it.descripcion()) }
            println("Subtotal: $subtotal")
            println("Descuento ($tipoUsuario): -$descuento")
            println("Multa por retraso: +$multa")
            println("Total a pagar: $total")
        }

        is EstadoPrestamo.Error -> {
            println("❌ Error: ${estado.msg}")
        }

        EstadoPrestamo.Pendiente -> {
            println("⏳ El préstamo está pendiente.")
        }

        EstadoPrestamo.Devuelto -> {
            println("📦 El libro ya fue devuelto.")
        }
    }
}

