package com.cabrera.practica04

fun generarRecibo(
    nombreCliente: String,
    monto: Double,
    descuento: Double = 0.0,
    impuesto: Double = 0.18,
    moneda: String = "DOP"
): String {
    val subtotal = monto - (monto * descuento)
    val impuestoVal = subtotal * impuesto
    val total = subtotal + impuestoVal
    return """
    ===========================
    RECIBO — $moneda
    Cliente   : $nombreCliente
    Subtotal  : %.2f
    Descuento : %.0f%%
    Impuesto  : %.2f
    Total     : %.2f
    ===========================
    """.trimIndent().format(subtotal, descuento * 100, impuestoVal, total)
}

fun String.esEmail(): Boolean = contains('@') && contains('.')
fun String.aTitulo(): String = split(' ').joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
fun String.mascararTarjeta(): String = "*".repeat(12) + takeLast(4)

fun List<Int>.promedio(): Double = if (isEmpty()) 0.0 else sum().toDouble() / size
fun List<Int>.aprobados(): List<Int> = filter { it >= 70 }
fun List<Int>.estadisticas() {
    if (isNotEmpty()) println("Min: ${minOrNull()}, Max: ${maxOrNull()}, Prom: ${promedio()}")
}

data class Pedido(val id: Int, var producto: String, var precio: Double, var activo: Boolean = true)

fun main() {
    println("4532847291823746".mascararTarjeta())
    val pedidos = listOf(Pedido(1, "Laptop", 45000.0), Pedido(2, "Mouse", 1200.0))
    pedidos.maxByOrNull { it.precio }?.let { println("Producto más caro: ${it.producto}") }
}