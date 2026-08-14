package com.cabrera.practica09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. MODELO DE DATOS
data class ElementoItem(
    val id: Int,
    val titulo: String,
    val categoria: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Practica09Pantalla(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// ==========================================
// PANTALLA PRINCIPAL DE LA PRÁCTICA 9
// ==========================================
@Composable
fun Practica09Pantalla(modifier: Modifier = Modifier) {
    var nuevoTexto by remember { mutableStateOf("") }

    // Lista mutable reactiva de Compose
    val listaElementos = remember {
        mutableStateListOf(
            ElementoItem(1, "Estudiar Jetpack Compose", "Universidad"),
            ElementoItem(2, "Revisar proyecto final", "Programación"),
            ElementoItem(3, "Configurar base de datos", "Desarrollo"),
            ElementoItem(4, "Subir cambios a GitHub", "Control de Versiones")
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título de la práctica
        Text(
            text = "Práctica 9: Listas con LazyColumn",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        // Formulario para agregar nuevos elementos a la lista
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = nuevoTexto,
                onValueChange = { nuevoTexto = it },
                label = { Text("Nuevo elemento") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    if (nuevoTexto.isNotBlank()) {
                        listaElementos.add(
                            ElementoItem(
                                id = listaElementos.size + 1,
                                titulo = nuevoTexto,
                                categoria = "General"
                            )
                        )
                        nuevoTexto = ""
                    }
                }
            ) {
                Text("Agregar")
            }
        }

        // Contador dinámico
        Text(
            text = "Total de registros: ${listaElementos.size}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.secondary
        )

        // 2. LISTA DESPLAZABLE OPTIMIZADA (LazyColumn)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(listaElementos) { item ->
                TarjetaElemento(
                    item = item,
                    onEliminar = { listaElementos.remove(item) }
                )
            }
        }
    }
}

// ==========================================
// COMPONENTE TARJETA (CARD)
// ==========================================
@Composable
fun TarjetaElemento(
    item: ElementoItem,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Categoría: ${item.categoria}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            OutlinedButton(onClick = onEliminar) {
                Text("Borrar")
            }
        }
    }
}

// ==========================================
// VISTA PREVIA (PREVIEW)
// ==========================================
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Practica09Preview() {
    MaterialTheme {
        Practica09Pantalla()
    }
}