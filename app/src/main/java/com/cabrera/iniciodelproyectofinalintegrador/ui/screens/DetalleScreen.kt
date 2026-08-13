package com.cabrera.iniciodelproyectofinalintegrador.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cabrera.iniciodelproyectofinalintegrador.data.listaDePeliculas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(id: Int, onVolver: () -> Unit) {
    val pelicula = listaDePeliculas.find { it.id == id }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Detalle de Película") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (pelicula != null) {
                Text(pelicula.titulo, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(pelicula.genero, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(16.dp))
                Text(pelicula.descripcion, style = MaterialTheme.typography.bodyLarge)
            } else {
                Text("Película no encontrada")
            }
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedButton(onClick = onVolver) {
                Text("Regresar")
            }
        }
    }
}