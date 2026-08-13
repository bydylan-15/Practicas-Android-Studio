package com.cabrera.iniciodelproyectofinalintegrador.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(onNavegarALista: () -> Unit) {
    var peliculasVistas by remember { mutableStateOf(0) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("MovieFavs") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Bienvenido a MovieFavs", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            Text("Películas marcadas como vistas: $peliculasVistas")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { peliculasVistas++ }) {
                Text("Marcar película vista (+1)")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = onNavegarALista) {
                Text("Ver Mi Lista de Películas")
            }
        }
    }
}