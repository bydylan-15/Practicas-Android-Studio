package com.cabrera.practica07

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaInicio(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// ==========================================
// PANTALLA PRINCIPAL DE LA PRÁCTICA 7
// ==========================================
@Composable
fun PantallaInicio(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // En lugar del icono de librería que da error, usamos el emoji de Android
        Text(
            text = "🤖",
            fontSize = 64.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título principal
        Text(
            text = "¡Mi primera app Android!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón 1
        Button(
            onClick = {
                Log.d("PrimerApp", "¡El botón de acción fue presionado!")
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Presióname")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón 2
        OutlinedButton(
            onClick = {
                Log.i("PrimerApp", "Desarrollador: Dylan Cabrera")
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Ver Desarrollador")
        }
    }
}

// ==========================================
// VISTA PREVIA (PREVIEW)
// ==========================================
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PantallaInicioPreview() {
    MaterialTheme {
        PantallaInicio()
    }
}