package com.cabrera.iniciodelproyectofinalintegrador.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cabrera.iniciodelproyectofinalintegrador.ui.screens.DetalleScreen
import com.cabrera.iniciodelproyectofinalintegrador.ui.screens.InicioScreen
import com.cabrera.iniciodelproyectofinalintegrador.ui.screens.ListaScreen

sealed class Pantalla(val ruta: String) {
    object Inicio : Pantalla("inicio")
    object Lista : Pantalla("lista")
    object Detalle : Pantalla("detalle/{peliculaId}") {
        fun crearRuta(id: Int) = "detalle/$id"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Pantalla.Inicio.ruta) {
        composable(Pantalla.Inicio.ruta) {
            InicioScreen(onNavegarALista = { navController.navigate(Pantalla.Lista.ruta) })
        }
        composable(Pantalla.Lista.ruta) {
            ListaScreen(onPeliculaClick = { id -> navController.navigate(Pantalla.Detalle.crearRuta(id)) })
        }
        composable(
            route = Pantalla.Detalle.ruta,
            arguments = listOf(navArgument("peliculaId") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("peliculaId") ?: 0
            DetalleScreen(id = id, onVolver = { navController.popBackStack() })
        }
    }
}