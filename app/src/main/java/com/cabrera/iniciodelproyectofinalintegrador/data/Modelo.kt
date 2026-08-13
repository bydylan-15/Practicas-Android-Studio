package com.cabrera.iniciodelproyectofinalintegrador.data

data class Pelicula(
    val id: Int,
    val titulo: String,
    val genero: String,
    val descripcion: String
)

val listaDePeliculas = listOf(
    Pelicula(
        id = 1,
        titulo = "Interstellar",
        genero = "Ciencia Ficción",
        descripcion = "Un grupo de exploradores viaja a través de un agujero de gusano en el espacio para asegurar la supervivencia de la humanidad."
    ),
    Pelicula(
        id = 2,
        titulo = "The Dark Knight",
        genero = "Acción / Drama",
        descripcion = "Batman se enfrenta al Caos encarnado por el Guasón en la ciudad de Gotham."
    ),
    Pelicula(
        id = 3,
        titulo = "Inception",
        genero = "Ciencia Ficción",
        descripcion = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños recibe la tarea inversa de plantar una idea."
    )
)