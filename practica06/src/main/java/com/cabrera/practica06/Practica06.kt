package com.cabrera.practica06

// ==========================================
// 1. ENUM CLASS: Prioridad
// ==========================================
enum class Prioridad(val nivel: Int, val codigoColor: String) {
    BAJA(1, "#GREEN"),
    MEDIA(2, "#YELLOW"),
    ALTA(3, "#ORANGE"),
    CRITICA(4, "#RED");

    fun esUrgente(): Boolean = this == ALTA || this == CRITICA
}

// ==========================================
// 2. DATA CLASS: Tarea
// ==========================================
data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String = "",
    val prioridad: Prioridad = Prioridad.MEDIA,
    var completada: Boolean = false
)

// ==========================================
// 3. SEALED CLASS: EstadoConexion
// ==========================================
sealed class EstadoConexion {
    object Conectado : EstadoConexion()
    object Desconectado : EstadoConexion()
    data class Reconectando(val intentos: Int) : EstadoConexion()
    data class Error(val mensaje: String) : EstadoConexion()
}

// Función que maneja exhaustivamente los estados con 'when'
fun manejarEstadoConexion(estado: EstadoConexion): String = when (estado) {
    is EstadoConexion.Conectado -> "🟢 Conexión establecida con éxito."
    is EstadoConexion.Desconectado -> "🔴 Sin conexión a Internet."
    is EstadoConexion.Reconectando -> "🟡 Reintentando conexión (Intento ${estado.intentos})..."
    is EstadoConexion.Error -> "❌ Error grave de red: ${estado.mensaje}"
}

// ==========================================
// 4. FUNCIÓN PRINCIPAL MAIN (Pruebas)
// ==========================================
fun main() {
    println("==================================================")
    println("      PRÁCTICA 6: CLASES ESPECIALES EN KOTLIN    ")
    println("==================================================\n")

    // --- Demostración de Enum Class ---
    println("--- 1. USO DE ENUMS ---")
    val p1 = Prioridad.CRITICA
    println("Prioridad: $p1 | Nivel: ${p1.nivel} | Color: ${p1.codigoColor}")
    println("¿Es urgente?: ${p1.esUrgente()}\n")

    // --- Demostración de Data Class ---
    println("--- 2. USO DE DATA CLASSES ---")
    val tarea1 = Tarea(1, "Estudiar para examen de Kotlin", "Repasar enums y data classes", Prioridad.ALTA)
    val tarea2 = tarea1.copy(id = 2, titulo = "Subir Práctica 6 a GitHub", prioridad = Prioridad.CRITICA)

    println("Tarea 1 original: $tarea1")
    println("Tarea 2 (Copia modificada): $tarea2")

    // Desestructuración de Data Class
    val (id, titulo, _, prioridad) = tarea2
    println("Desestructuración -> ID: $id | Título: '$titulo' | Prioridad: $prioridad\n")

    // --- Demostración de Sealed Class ---
    println("--- 3. USO DE SEALED CLASSES ---")
    val estados: List<EstadoConexion> = listOf(
        EstadoConexion.Desconectado,
        EstadoConexion.Reconectando(1),
        EstadoConexion.Reconectando(2),
        EstadoConexion.Conectado,
        EstadoConexion.Error("Tiempo de espera agotado (Timeout)")
    )

    for (e in estados) {
        println(manejarEstadoConexion(e))
    }

    println("\n==================================================")
    println("      ¡PRÁCTICA 6 COMPLETADA EXITOSAMENTE!       ")
    println("==================================================")
}