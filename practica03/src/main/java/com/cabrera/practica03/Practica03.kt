package com.cabrera.practica03

data class ResultadoValidacion(val esValido: Boolean, val mensaje: String)

fun validarUsuario(nombre: String?, email: String?): ResultadoValidacion {
    val nombreLimpio = nombre?.trim()
    val longitudNombre = nombreLimpio?.length ?: 0
    if (longitudNombre < 3) {
        return ResultadoValidacion(false, "Nombre muy corto o vacío")
    }

    // TODO 1 & 2 resueltos:
    val emailValido = email?.contains('@') ?: false
    if (!emailValido) {
        return ResultadoValidacion(false, "Email inválido")
    }

    return ResultadoValidacion(true, "Usuario '$nombreLimpio' registrado correctamente")
}

fun analizarNotas(notas: List<Int>) {
    if (notas.isEmpty()) return
    val promedio = notas.average()
    val aprobados = notas.filter { it >= 70 }
    val reprobados = notas.filter { it < 70 }
    val ordenadas = notas.sortedByDescending { it }

    println("Total estudiantes : ${notas.size}")
    println("Promedio : %.2f".format(promedio))
    println("Aprobados : ${aprobados.size}")
    println("Reprobados : ${reprobados.size}")
    println("Nota más alta : ${ordenadas.first()}")
    println("Nota más baja : ${ordenadas.last()}")

    // TODOs resueltos:
    val aprobadosOrdenados = aprobados.sortedByDescending { it }
    println("Notas aprobadas ordenadas: $aprobadosOrdenados")
    val pctAprobados = (aprobados.size.toDouble() / notas.size) * 100
    println("Porcentaje de aprobados: %.2f%%".format(pctAprobados))
}

fun main() {
    val estudiantes = mapOf(
        "Ana López" to 92, "Carlos Ruiz" to 65, "María Díaz" to 88,
        "Pedro Soto" to 55, "Laura Vega" to 75, "Juan Torres" to 48, "Sofía Reyes" to 91
    )

    val grupos = estudiantes.entries.groupBy { (_, nota) ->
        when {
            nota >= 90 -> "Sobresaliente"
            nota >= 70 -> "Aprobado"
            else -> "Reprobado"
        }
    }

    // TODOs resueltos:
    grupos.forEach { (cat, lista) -> println("$cat: ${lista.size} estudiantes") }
    val mejorEstudiante = estudiantes.maxByOrNull { it.value }
    println("Mejor estudiante: ${mejorEstudiante?.key} con ${mejorEstudiante?.value}")
}