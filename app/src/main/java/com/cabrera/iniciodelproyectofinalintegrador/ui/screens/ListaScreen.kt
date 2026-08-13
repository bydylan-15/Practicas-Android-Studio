package com.cabrera.iniciodelproyectofinalintegrador.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cabrera.iniciodelproyectofinalintegrador.data.listaDePeliculas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaScreen(onPeliculaClick: (Int) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Películas Favoritas") }) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(listaDePeliculas) { pelicula ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onPeliculaClick(pelicula.id) },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(pelicula.titulo, style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(pelicula.genero, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}