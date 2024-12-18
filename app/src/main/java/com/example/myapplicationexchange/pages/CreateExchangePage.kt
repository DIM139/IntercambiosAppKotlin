package com.example.myapplicationexchange.pages



import android.widget.DatePicker
import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

@Composable
fun CreateExchangePage(navController: NavController) {
    val firestore = FirebaseFirestore.getInstance()

    // Estados para los campos del formulario
    var exchangeName by remember { mutableStateOf("") }
//    var participantsList by remember { mutableStateOf(mutableListOf<Map<String, String>>()) }
    var themes by remember { mutableStateOf("") }
    var maxAmount by remember { mutableStateOf("") }
    var exchangeDate by remember { mutableStateOf("") }
    var exchangePlace by remember { mutableStateOf("") }
    var deadlineDate by remember { mutableStateOf("") }
    var exchangeId by remember { mutableStateOf("") }

    // Estados para participantes
    var participantName by remember { mutableStateOf("") }
    var participantEmail by remember { mutableStateOf("") }
    var participantPhone by remember { mutableStateOf("") }
    val participantsList = remember { mutableStateListOf<Map<String, String>>() }

    val context = LocalContext.current
    // Scroll state
    val scrollState = rememberScrollState()

    // Layout principal
    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState), // Habilitar el scroll
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Intercambio", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = exchangeId,
            onValueChange = { exchangeId = it },
            label = { Text("ID del Intercambio") },
            placeholder = { Text("Ej: c0925b01-9005-416d-b353") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = exchangeName,
            onValueChange = { exchangeName = it },
            label = { Text("Nombre del Intercambio") }
        )

        Spacer(modifier = Modifier.height(8.dp))

//        Text("Participantes")
//        participantsList.forEachIndexed { index, participant ->
//            Column {
//                Text("Nombre: ${participant["nombre"]}, Correo: ${participant["correo"]}, Teléfono: ${participant["telefono"]}")
//                Spacer(modifier = Modifier.height(4.dp))
//            }
//        }
//
//        Button(onClick = {
//            participantsList.add(
//                mapOf(
//                    "nombre" to "Nuevo Participante",
//                    "correo" to "email@example.com",
//                    "telefono" to "1234567890"
//                )
//            )
//        }) {
//            Text("Agregar Participante")
//        }

        // Participantes
        Text("Agregar Participantes", fontSize = 18.sp)

        OutlinedTextField(
            value = participantName,
            onValueChange = { participantName = it },
            label = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = participantEmail,
            onValueChange = { participantEmail = it },
            label = { Text("Correo") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = participantPhone,
            onValueChange = { participantPhone = it },
            label = { Text("Teléfono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (participantName.isNotBlank() && participantEmail.isNotBlank() && participantPhone.isNotBlank()) {
                participantsList.add(
                    mapOf(
                        "nombre" to participantName,
                        "correo" to participantEmail,
                        "telefono" to participantPhone
                    )
                )
                participantName = ""
                participantEmail = ""
                participantPhone = ""
            }
        }) {
            Text("Agregar Participante")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Lista de Participantes", fontSize = 18.sp)

        participantsList.forEachIndexed { index, participant ->
            Text("${index + 1}. ${participant["nombre"]} - ${participant["correo"]} - ${participant["telefono"]}")
        }


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

        // Selección de fecha de intercambio
        Button(onClick = {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                context,
                { _, year, month, day ->
                    exchangeDate = "$year-${month + 1}-$day"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }) {
            Text("Seleccionar Fecha de Intercambio")
        }
        Text("Fecha seleccionada: $exchangeDate")

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = exchangePlace,
            onValueChange = { exchangePlace = it },
            label = { Text("Lugar del intercambio") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Selección de fecha límite
        Button(onClick = {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                context,
                { _, year, month, day ->
                    deadlineDate = "$year-${month + 1}-$day"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }) {
            Text("Seleccionar Fecha Límite")
        }
        Text("Fecha límite seleccionada: $deadlineDate")

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar
        Button(onClick = {
            if (exchangeId.isEmpty()) {
                exchangeId = UUID.randomUUID().toString()
            }

            val themesList = themes.split(",")
            val exchangeData = mapOf(
                "id" to exchangeId,
                "nombre" to exchangeName,
                "participantes" to participantsList,
                "temas" to themesList,
                "monto_maximo" to maxAmount.toInt(),
                "fecha_intercambio" to exchangeDate,
                "lugar" to exchangePlace,
                "fecha_limite" to deadlineDate
            )

            // Guardar en Firestore
            firestore.collection("intercambios").document(exchangeId).set(exchangeData)
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


