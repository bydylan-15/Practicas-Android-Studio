package com.cabrera.practica11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

// ==========================================
// 1. MODELO DE DATOS
// ==========================================
data class TareaModel(
    val id: Int,
    val titulo: String,
    val completada: Boolean = false
)

// ==========================================
// 2. VIEWMODEL
// ==========================================
class Practica11ViewModel : ViewModel() {

    private val _listaTareas = mutableStateListOf(
        TareaModel(1, "Instalar dependencias de Jetpack Compose", true),
        TareaModel(2, "Estudiar patrón de diseño MVVM", false),
        TareaModel(3, "Completar Práctica 11 de Android", false)
    )

    val listaTareas: List<TareaModel> get() = _listaTareas

    fun agregarTarea(titulo: String) {
        if (titulo.isNotBlank()) {
            _listaTareas.add(
                TareaModel(
                    id = System.currentTimeMillis().toInt(),
                    titulo = titulo.trim()
                )
            )
        }
    }

    fun cambiarEstadoTarea(id: Int) {
        val index = _listaTareas.indexOfFirst { it.id == id }
        if (index != -1) {
            val tareaActual = _listaTareas[index]
            _listaTareas[index] = tareaActual.copy(completada = !tareaActual.completada)
        }
    }

    fun eliminarTarea(id: Int) {
        _listaTareas.removeIf { it.id == id }
    }
}

// ==========================================
// 3. ACTIVIDAD PRINCIPAL
// ==========================================
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Practica11Pantalla(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// ==========================================
// 4. INTERFAZ GRÁFICA (VISTA)
// ==========================================
@Composable
fun Practica11Pantalla(
    modifier: Modifier = Modifier,
    viewModel: Practica11ViewModel = remember { Practica11ViewModel() }
) {
    var nuevoTextoTarea by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Práctica 11: ViewModel & MVVM",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = nuevoTextoTarea,
                onValueChange = { nuevoTextoTarea = it },
                label = { Text("Nueva tarea pendiente") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    viewModel.agregarTarea(nuevoTextoTarea)
                    nuevoTextoTarea = ""
                }
            ) {
                Text("Guardar")
            }
        }

        val pendientes = viewModel.listaTareas.count { !it.completada }
        val completadas = viewModel.listaTareas.count { it.completada }

        Text(
            text = "Pendientes: $pendientes | Completadas: $completadas",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.secondary
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.listaTareas, key = { it.id }) { tarea ->
                ItemTarea(
                    tarea = tarea,
                    onToggle = { viewModel.cambiarEstadoTarea(tarea.id) },
                    onEliminar = { viewModel.eliminarTarea(tarea.id) }
                )
            }
        }
    }
}

// ==========================================
// COMPONENTE TARJETA DE TAREA
// ==========================================
@Composable
fun ItemTarea(
    tarea: TareaModel,
    onToggle: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        colors = CardDefaults.cardColors(
            containerColor = if (tarea.completada)
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            else
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = tarea.completada,
                    onCheckedChange = { onToggle() }
                )
                Text(
                    text = tarea.titulo,
                    fontWeight = FontWeight.Medium,
                    textDecoration = if (tarea.completada) TextDecoration.LineThrough else TextDecoration.None,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            OutlinedButton(onClick = onEliminar) {
                Text("❌")
            }
        }
    }
}

// ==========================================
// VISTA PREVIA (PREVIEW)
// ==========================================
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Practica11Preview() {
    MaterialTheme {
        Practica11Pantalla()
    }
}