package com.cabrera.practica05

// ==========================================
// 1. INTERFAZ: Evaluable
// ==========================================
interface Evaluable {
    val notaMinima: Int get() = 70

    fun estaAprobado(nota: Int): Boolean = nota >= notaMinima

    fun descripcion(): String
}

// ==========================================
// 2. CLASE BASE: Persona (Usa 'open' para permitir herencia)
// ==========================================
open class Persona(
    val nombre: String,
    val apellido: String,
    val cedula: String
) {
    val nombreCompleto: String
        get() = "$nombre $apellido"

    init {
        // Validación de cédula (11 dígitos)
        require(cedula.length == 11 && cedula.all { it.isDigit() }) {
            "La cédula debe contener exactamente 11 dígitos numéricos."
        }
    }

    open fun presentarse(): String {
        return "Hola, soy $nombreCompleto (Cédula: $cedula)"
    }
}

// ==========================================
// 3. SUBCLASE: Estudiante (Hereda de Persona e implementa Evaluable)
// ==========================================
class Estudiante(
    nombre: String,
    apellido: String,
    cedula: String,
    val matricula: String,
    val carrera: String
) : Persona(nombre, apellido, cedula), Evaluable {

    private val _notas = mutableListOf<Int>()
    val notas: List<Int> get() = _notas

    fun agregarNota(nota: Int) {
        if (nota in 0..100) {
            _notas.add(nota)
        } else {
            println("❌ Nota $nota no válida. Debe estar entre 0 y 100.")
        }
    }

    val promedio: Double
        get() = if (_notas.isEmpty()) 0.0 else _notas.average()

    override fun presentarse(): String {
        return "${super.presentarse()} | Estudiante de $carrera (Matrícula: $matricula)"
    }

    override fun descripcion(): String {
        val estado = if (estaAprobado(promedio.toInt())) "APROBADO" else "EN RIESGO"
        return "Estudiante: $nombreCompleto | Promedio: %.1f | Estado: $estado".format(promedio)
    }
}

// ==========================================
// 4. SUBCLASE: Docente (Hereda de Persona e implementa Evaluable)
// ==========================================
class Docente(
    nombre: String,
    apellido: String,
    cedula: String,
    val departamento: String,
    val añosExperiencia: Int
) : Persona(nombre, apellido, cedula), Evaluable {

    override fun presentarse(): String {
        return "${super.presentarse()} | Docente de $departamento ($añosExperiencia años de exp.)"
    }

    override fun descripcion(): String {
        return "Docente: $nombreCompleto | Departamento: $departamento"
    }
}

// ==========================================
// 5. FUNCIÓN PRINCIPAL MAIN (Pruebas)
// ==========================================
fun main() {
    println("==================================================")
    println("     PRÁCTICA 5: CLASES, HERENCIA Y POO          ")
    println("==================================================\n")

    try {
        // --- Prueba Estudiante ---
        val estudiante = Estudiante(
            nombre = "Dylan",
            apellido = "Cabrera",
            cedula = "40231980281",
            matricula = "2023-0021",
            carrera = "Ingeniería de Software"
        )

        estudiante.agregarNota(95)
        estudiante.agregarNota(88)
        estudiante.agregarNota(92)

        println("--- DATOS DEL ESTUDIANTE ---")
        println(estudiante.presentarse())
        println(estudiante.descripcion())
        println("¿Está Aprobado?: ${estudiante.estaAprobado(estudiante.promedio.toInt())}\n")

        // --- Prueba Docente ---
        val docente = Docente(
            nombre = "Carlos",
            apellido = "Martínez",
            cedula = "00198765432",
            departamento = "Tecnología y Sistemas",
            añosExperiencia = 8
        )

        println("--- DATOS DEL DOCENTE ---")
        println(docente.presentarse())
        println(docente.descripcion())

    } catch (e: IllegalArgumentException) {
        println("❌ Error de validación: ${e.message}")
    }
}