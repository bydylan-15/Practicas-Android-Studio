package com.cabrera.practica10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
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

// ==========================================
// 1. MODELO DE DATOS Y PANTALLAS
// ==========================================
data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val descripcion: String
)

sealed class Destino {
    object Lista : Destino()
    object Formulario : Destino()
    data class Detalle(val producto: Producto) : Destino()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Practica10App(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// ==========================================
// CONTENEDOR PRINCIPAL Y NAVEGACIÓN
// ==========================================
@Composable
fun Practica10App(modifier: Modifier = Modifier) {
    // Estado de la pantalla actual
    var destinoActual by remember { mutableStateOf<Destino>(Destino.Lista) }

    // Lista inicial de datos
    val productos = remember {
        mutableStateListOf(
            Producto(1, "Laptop Gamer", 1200.0, "Intel i7, 16GB RAM, RTX 4060"),
            Producto(2, "Teclado Mecánico", 85.0, "Switches Red, Iluminación RGB"),
            Producto(3, "Monitor 144Hz", 250.0, "24 Pulgadas IPS, 1ms respuesta")
        )
    }

    Column(modifier = modifier.fillMaxSize()) {
        when (val pantalla = destinoActual) {
            is Destino.Lista -> PantallaListaProductos(
                productos = productos,
                onIrAFormulario = { destinoActual = Destino.Formulario },
                onVerDetalle = { prod -> destinoActual = Destino.Detalle(prod) }
            )
            is Destino.Formulario -> PantallaNuevoProducto(
                onGuardar = { nuevoProd ->
                    productos.add(nuevoProd)
                    destinoActual = Destino.Lista
                },
                onVolver = { destinoActual = Destino.Lista }
            )
            is Destino.Detalle -> PantallaDetalleProducto(
                producto = pantalla.producto,
                onVolver = { destinoActual = Destino.Lista }
            )
        }
    }
}

// ==========================================
// PANTALLA 1: LISTA DE PRODUCTOS
// ==========================================
@Composable
fun PantallaListaProductos(
    productos: List<Producto>,
    onIrAFormulario: () -> Unit,
    onVerDetalle: (Producto) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Práctica 10: Navegación",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Button(onClick = onIrAFormulario) {
                Text("+ Nuevo")
            }
        }

        Text(
            text = "Toca un elemento para ver su detalle:",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(productos) { prod ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onVerDetalle(prod) },
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
                        Column {
                            Text(text = prod.nombre, fontWeight = FontWeight.Bold)
                            Text(text = "$${prod.precio}", fontSize = 14.sp)
                        }
                        Text(text = "Ver ➡️", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

// ==========================================
// PANTALLA 2: FORMULARIO DE REGISTRO
// ==========================================
@Composable
fun PantallaNuevoProducto(
    onGuardar: (Producto) -> Unit,
    onVolver: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Registrar Nuevo Producto",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del Producto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio ($)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onVolver,
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = {
                    if (nombre.isNotBlank() && precio.isNotBlank()) {
                        onGuardar(
                            Producto(
                                id = (10..999).random(),
                                nombre = nombre,
                                precio = precio.toDoubleOrNull() ?: 0.0,
                                descripcion = descripcion
                            )
                        )
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Guardar")
            }
        }
    }
}

// ==========================================
// PANTALLA 3: DETALLE DEL PRODUCTO
// ==========================================
@Composable
fun PantallaDetalleProducto(
    producto: Producto,
    onVolver: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedButton(onClick = onVolver) {
            Text("⬅️ Volver a la Lista")
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = producto.nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Precio: $${producto.precio}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "ID de Registro: #${producto.id}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.outline
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Descripción del producto:",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = if (producto.descripcion.isBlank()) "Sin descripción provista." else producto.descripcion,
                    fontSize = 16.sp
                )
            }
        }
    }
}

// ==========================================
// VISTA PREVIA (PREVIEW)
// ==========================================
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Practica10Preview() {
    MaterialTheme {
        Practica10App()
    }
}