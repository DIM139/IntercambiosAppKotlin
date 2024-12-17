package com.example.myapplicationexchange.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

@Composable
fun CreateExchangePage(navController: NavController) {///aten
    val firestore = FirebaseFirestore.getInstance()

    // Estados para los campos del formulario
    var exchangeName by remember { mutableStateOf("") }
    var participants by remember { mutableStateOf("") } // Añadir manualmente
    var themes by remember { mutableStateOf("") }
    var maxAmount by remember { mutableStateOf("") }
    var exchangeDate by remember { mutableStateOf("") }
    var exchangePlace by remember { mutableStateOf("") }
    var deadlineDate by remember { mutableStateOf("") }

    // Layout principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Intercambio", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Campos de texto
        OutlinedTextField(
            value = exchangeName,
            onValueChange = { exchangeName = it },
            label = { Text("Nombre del Intercambio") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = participants,
            onValueChange = { participants = it },
            label = { Text("Participantes (nombre,correo,telefono)") },
            placeholder = { Text("Ej: Juan,juan@example.com,1234567890") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = themes,
            onValueChange = { themes = it },
            label = { Text("Temas del regalo (separados por comas)") },
            placeholder = { Text("Ej: libros,tazas,ropa") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = maxAmount,
            onValueChange = { maxAmount = it },
            label = { Text("Monto máximo (MXN)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = exchangeDate,
            onValueChange = { exchangeDate = it },
            label = { Text("Fecha y hora del intercambio (YYYY-MM-DD HH:MM)") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = exchangePlace,
            onValueChange = { exchangePlace = it },
            label = { Text("Lugar del intercambio") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = deadlineDate,
            onValueChange = { deadlineDate = it },
            label = { Text("Fecha límite de registro (YYYY-MM-DD)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar
        Button(onClick = {
            val id = UUID.randomUUID().toString() // Clave única

            val participantsList = participants.split(";").map {
                val data = it.split(",")
                mapOf("nombre" to data[0], "correo" to data[1], "telefono" to data[2])
            }

            val themesList = themes.split(",")

            val exchangeData = mapOf(
                "id" to id,
                "nombre" to exchangeName,
                "participantes" to participantsList,
                "temas" to themesList,
                "monto_maximo" to maxAmount.toInt(),
                "fecha_intercambio" to exchangeDate,
                "lugar" to exchangePlace,
                "fecha_limite" to deadlineDate
            )

            // Guardar en Firestore
            firestore.collection("intercambios").document(id).set(exchangeData)
                .addOnSuccessListener {
                    println("Intercambio guardado exitosamente")
                    navController.navigate("home")
                }
                .addOnFailureListener { e ->
                    println("Error al guardar: $e")
                }
        }) {
            Text("Guardar Intercambio")
        }
    }
}