package com.cabrera.practica02

fun clasificarNota(nota: Double): String = when {
    nota < 0.0 || nota > 100.0 -> "Nota inválida"
    nota >= 90.0 -> "Sobresaliente (A)"
    nota >= 80.0 -> "Muy Bueno (B)"
    nota >= 70.0 -> "Bueno (C)"
    nota >= 60.0 -> "Aprobado (D)"
    else -> "Reprobado (F)"
}

fun fizzBuzz() {
    for (i in 1..30) {
        val resultado = when {
            i % 15 == 0 -> "FizzBuzz"
            i % 3 == 0 -> "Fizz"
            i % 5 == 0 -> "Buzz"
            else -> "$i"
        }
        print("$resultado ")
        if (i % 10 == 0) println()
    }
}

fun tablaMultiplicar(n: Int) {
    println("\n=== Tabla del $n ===")
    for (i in 1..10) {
        println("$n x $i = ${n * i}")
    }
}

fun main() {
    val nombreCurso = "Programación Móvil I"
    val añoInicio: Int = 2026
    var calificacion: Double = 95.5

    // TODO 1, 2, 3 resueltos:
    val miNombre = "Dylan Cabrera"
    var miEdad = 20
    println("Me llamo $miNombre y tengo $miEdad años")

    // Pruebas clasificarNota (acepta Double)
    val notas = listOf(100.0, 92.5, 85.0, 73.0, 61.0, 45.0, -5.0)
    for (nota in notas) {
        println("Nota $nota -> ${clasificarNota(nota)}")
    }

    fizzBuzz()

    print("\nIngresa un número para la tabla: ")
    val num = readLine()?.toIntOrNull() ?: 7
    tablaMultiplicar(num)
}