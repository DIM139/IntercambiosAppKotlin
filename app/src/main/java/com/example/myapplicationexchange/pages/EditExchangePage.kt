package com.example.myapplicationexchange.pages

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.ui.platform.LocalContext

import java.util.*

@Composable
fun EditExchangePage(exchangeId: String, navController: NavController) {
    val firestore = FirebaseFirestore.getInstance()
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    // Estados para los campos del intercambio
    var exchangeName by remember { mutableStateOf("") }
    var maxAmount by remember { mutableStateOf("") }
    var exchangeDate by remember { mutableStateOf("") }
    var exchangePlace by remember { mutableStateOf("") }
    var deadlineDate by remember { mutableStateOf("") }
    var themes by remember { mutableStateOf("") }
    val participantsList = remember { mutableStateListOf<Map<String, String>>() }

    // Cargar datos del intercambio
    LaunchedEffect(exchangeId) {
        firestore.collection("intercambios").document(exchangeId)
            .get()
            .addOnSuccessListener { document ->
                exchangeName = document.getString("nombre") ?: ""
                maxAmount = document.getDouble("monto_maximo")?.toInt()?.toString() ?: ""
                exchangeDate = document.getString("fecha_intercambio") ?: ""
                exchangePlace = document.getString("lugar") ?: ""
                deadlineDate = document.getString("fecha_limite") ?: ""
                themes = (document.get("temas") as? List<String>)?.joinToString(", ") ?: ""
                val participants = document.get("participantes") as? List<Map<String, String>> ?: emptyList()
                participantsList.clear()
                participantsList.addAll(participants)
            }
    }

    // UI de edición
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Editar Intercambio", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = exchangeName,
            onValueChange = { exchangeName = it },
            label = { Text("Nombre del Intercambio") },
            placeholder = { Text("Ej: Fiesta de Fin de Año") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = maxAmount,
            onValueChange = { maxAmount = it },
            label = { Text("Monto Máximo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text("Ej: 500") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = exchangeDate,
            onValueChange = { exchangeDate = it },
            label = { Text("Fecha de Intercambio") },
            placeholder = { Text("Ej: 25/12/2023") },
            modifier = Modifier.fillMaxWidth(),

        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = exchangePlace,
            onValueChange = { exchangePlace = it },
            label = { Text("Lugar de Intercambio") },
            placeholder = { Text("Ej: Casa de Juan") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = deadlineDate,
            onValueChange = { deadlineDate = it },
            label = { Text("Fecha Límite") },
            placeholder = { Text("Ej: 20/12/2023") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = themes,
            onValueChange = { themes = it },
            label = { Text("Temas") },
            placeholder = { Text("Ej: Regalos, Navidad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Participantes
        Text("Participantes:", fontSize = 18.sp)
        participantsList.forEach { participant ->
            Text("• ${participant["nombre"]} (${participant["correo"]})", fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar cambios
        Button(
            onClick = {
                val updatedExchange = mapOf(
                    "nombre" to exchangeName,
                    "monto_maximo" to maxAmount.toIntOrNull(),
                    "fecha_intercambio" to exchangeDate,
                    "lugar" to exchangePlace,
                    "fecha_limite" to deadlineDate,
                    "temas" to themes.split(", ").map { it.trim() },
                    "participantes" to participantsList
                )

                firestore.collection("intercambios").document(exchangeId)
                    .update(updatedExchange)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Intercambio actualizado", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(context, "Error al actualizar: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Cambios")
        }
    }
}

private fun showDatePicker(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            onDateSelected("$dayOfMonth/${month + 1}/$year")
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}
