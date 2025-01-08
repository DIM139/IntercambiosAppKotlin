package com.example.myapplicationexchange.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplicationexchange.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//ya chido
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ExchangeDetailsPage(exchangeId: String, navController: NavController) {
//    val db = Firebase.firestore
//    val exchangeState = remember { mutableStateOf<Exchange?>(null) }
//    val context = LocalContext.current
//    val sortedResult = remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }
//
//
//    // Recuperar detalles del intercambio desde Firestore
//    LaunchedEffect(exchangeId) {
//        db.collection("intercambios").document(exchangeId)
//            .get()
//            .addOnSuccessListener { document ->
//                val exchange = try {
//                    Exchange(
//                        id = document.getString("id") ?: "",
//                        nombre = document.getString("nombre") ?: "",
//                        participantes = (document.get("participantes") as? List<Map<String, Any>>)?.map {
//                            Participant(
//                                nombre = it["nombre"] as? String ?: "",
//                                correo = it["correo"] as? String ?: "",
//                                telefono = it["telefono"] as? String ?: ""
//                            )
//                        } ?: emptyList(),
//                        temas = (document.get("temas") as? List<String>) ?: emptyList(), // Aquí obtenemos los temas
//                        montoMaximo = document.getDouble("monto_maximo")?.toInt() ?: 0,
//                        fechaIntercambio = document.getString("fecha_intercambio") ?: "",
//                        lugar = document.getString("lugar") ?: "",
//                        fechaLimite = document.getString("fecha_limite") ?: ""
//                    )
//                } catch (e: Exception) {
//                    null
//                }
//                exchangeState.value = exchange
//            }
//            .addOnFailureListener { exception ->
//                Toast.makeText(context, "Error al cargar los detalles: ${exception.message}", Toast.LENGTH_SHORT).show()
//            }
//    }
//
//    // UI
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Detalles del Intercambio") },
//                colors = TopAppBarDefaults.mediumTopAppBarColors()
//            )
//        }
//    ) { innerPadding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .verticalScroll(rememberScrollState()) // Para permitir que todo el contenido sea desplazable
//        ) {
//            val exchange = exchangeState.value
//            if (exchange == null) {
//                // Mostrar mensaje si no se encontró el intercambio
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text("Cargando detalles del intercambio...")
//                }
//            } else {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    // Imagen representativa
//                    Image(
//                        painter = painterResource(id = R.drawable.ar_image), // Asegúrate de tener la imagen en res/drawable
//                        contentDescription = "Intercambio",
//                        modifier = Modifier.size(120.dp)
//                    )
//
//                    // Título de la aplicación
//                    Text(
//                        text = exchange.nombre,
//                        style = MaterialTheme.typography.headlineMedium,
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
//                    )
//
//                    // Detalles del intercambio
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Text(text = "Fecha de Intercambio: ${exchange.fechaIntercambio}", style = MaterialTheme.typography.bodyMedium)
//                        Text(text = "Lugar: ${exchange.lugar}", style = MaterialTheme.typography.bodyMedium)
//                        Text(text = "Fecha Límite: ${exchange.fechaLimite}", style = MaterialTheme.typography.bodyMedium)
//                        Text(text = "Monto Máximo: \$${exchange.montoMaximo}", style = MaterialTheme.typography.bodyMedium)
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        // Participantes
//                        Text(text = "Participantes:", style = MaterialTheme.typography.bodyMedium)
//                        exchange.participantes.forEach { participant ->
//                            Text(text = "• ${participant.nombre} - ${participant.correo}", style = MaterialTheme.typography.bodyMedium)
//                        }
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        // Temas
//                        Text(text = "Temas:", style = MaterialTheme.typography.bodyMedium)
//                        exchange.temas.forEach { tema ->
//                            Text(text = "• $tema", style = MaterialTheme.typography.bodyMedium)
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(32.dp))
//
//                    // Botones: Editar y Eliminar
//                    Row(
//                        horizontalArrangement = Arrangement.spacedBy(16.dp)
//                    ) {
//                        Button(
//                            onClick = {
//                                // Lógica para editar: navega a una pantalla de edición con los datos del intercambio
//                                navController.navigate("edit/${exchange.id}")
//                            },
//                            modifier = Modifier.width(120.dp)
//                        ) {
//                            Text("Editar")
//                        }
//
//                        Button(
//                            onClick = {
//                                // Lógica para eliminar el intercambio
//                                db.collection("intercambios").document(exchange.id)
//                                    .delete()
//                                    .addOnSuccessListener {
//                                        Toast.makeText(context, "Intercambio eliminado", Toast.LENGTH_SHORT).show()
//                                        // Regresar a la pantalla anterior o actualizar la UI
//                                        navController.popBackStack()
//                                    }
//                                    .addOnFailureListener { exception ->
//                                        Toast.makeText(context, "Error al eliminar: ${exception.message}", Toast.LENGTH_SHORT).show()
//                                    }
//                            },
//                            modifier = Modifier.width(120.dp)
//                        ) {
//                            Text("Eliminar")
//                        }
//                    }
//                    Button(
//                        onClick = {
//                            val participants = exchange.participantes.map { it.nombre }
//                            if (participants.size < 2) {
//                                Toast.makeText(context, "Se necesitan al menos 2 participantes", Toast.LENGTH_SHORT).show()
//                            } else {
//                                val shuffled = participants.shuffled()
//                                val result = shuffled.zip(shuffled.drop(1) + shuffled.take(1))
//                                sortedResult.value = result
//                                Toast.makeText(context, "¡Sorteo realizado!", Toast.LENGTH_SHORT).show()
//                            }
//                        },
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Text("Realizar Sorteo")
//                    }
//
////                    if (sortedResult.value.isNotEmpty()) {
////                        Text("Resultados del Sorteo:", style = MaterialTheme.typography.bodyMedium)
////                        sortedResult.value.forEach { (giver, receiver) ->
////                            Text("$giver → $receiver", style = MaterialTheme.typography.bodyMedium)
////
////                        }
////                    }
////                    exchange.participantes.forEachIndexed { _, participant ->
////                        val aux=participant.correo?:"dfrancohdez@gmail.com"
////                        sendEmail("Asignacion de Intercambio",
////                            "Todo preparado en "+exchange.nombre+" con el id: "+exchangeId ,aux)
////                    }
//                    // Enviar correos con la lista del sorteo
//                    sortedResult.value.forEach { (giver, receiver) ->
//                        val participant = exchange.participantes.find { it.nombre == giver }
//                        val aux = participant?.correo ?: "dfrancohdez@gmail.com"
//                        val message = buildString {
//                            append("Hola $giver,\n\n")
//                            append("¡Gracias por participar en el intercambio \"${exchange.nombre}\"!\n\n")
//                            append("Detalles del intercambio:\n")
//                            append("- Fecha: ${exchange.fechaIntercambio}\n")
//                            append("- Lugar: ${exchange.lugar}\n")
//                            append("- Monto Máximo: \$${exchange.montoMaximo}\n\n")
//                            append("A ti te toca dar un regalo a: $receiver.\n\n")
//                            append("Resultados completos del sorteo:\n")
//                            sortedResult.value.forEach { (giver, receiver) ->
//                                append("$giver → $receiver\n")
//                            }
//                            append("\n¡Nos vemos pronto!")
//                        }
//
//                        sendEmail(
//                            subject = "Asignación del Intercambio: ${exchange.nombre}",
//                            body = message,
//                            to = aux
//                        )
//                    }
//                    if (sortedResult.value.isNotEmpty()) {
//                        Text("Resultados del Sorteo:", style = MaterialTheme.typography.bodyMedium)
//                        sortedResult.value.forEach { (giver, receiver) ->
//                            Text("$giver → $receiver", style = MaterialTheme.typography.bodyMedium)
//
//                        }
//
//                }
//            }
//        }
//    }
//}}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeDetailsPage(exchangeId: String, navController: NavController) {
    val db = Firebase.firestore
    val exchangeState = remember { mutableStateOf<Exchange?>(null) }
    val context = LocalContext.current
    val sortedResult = remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }

    // Recuperar detalles del intercambio desde Firestore
    LaunchedEffect(exchangeId) {
        db.collection("intercambios").document(exchangeId)
            .get()
            .addOnSuccessListener { document ->
                val exchange = try {
                    Exchange(
                        id = document.getString("id") ?: "",
                        nombre = document.getString("nombre") ?: "",
                        participantes = (document.get("participantes") as? List<Map<String, Any>>)?.map {
                            Participant(
                                nombre = it["nombre"] as? String ?: "",
                                correo = it["correo"] as? String ?: "",
                                telefono = it["telefono"] as? String ?: ""
                            )
                        } ?: emptyList(),
                        temas = (document.get("temas") as? List<String>) ?: emptyList(),
                        montoMaximo = document.getDouble("monto_maximo")?.toInt() ?: 0,
                        fechaIntercambio = document.getString("fecha_intercambio") ?: "",
                        lugar = document.getString("lugar") ?: "",
                        fechaLimite = document.getString("fecha_limite") ?: ""
                    )
                } catch (e: Exception) {
                    null
                }
                exchangeState.value = exchange
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Error al cargar los detalles: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // UI
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Intercambio") },
                colors = TopAppBarDefaults.mediumTopAppBarColors()
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()) // Para permitir que todo el contenido sea desplazable
        ) {
            val exchange = exchangeState.value
            if (exchange == null) {
                // Mostrar mensaje si no se encontró el intercambio
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Cargando detalles del intercambio...")
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Imagen representativa con bordes redondeados
                    Image(
                        painter = painterResource(id = R.drawable.ar_image), // Asegúrate de tener la imagen en res/drawable
                        contentDescription = "Intercambio",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(MaterialTheme.shapes.medium) // Bordes redondeados
                    )

                    // Título con un color diferente y estilo mejorado
                    Text(
                        text = exchange.nombre,
                        style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    // Detalles del intercambio en tarjetas
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "Fecha de Intercambio: ${exchange.fechaIntercambio}", style = MaterialTheme.typography.bodyMedium)
                            Text(text = "Lugar: ${exchange.lugar}", style = MaterialTheme.typography.bodyMedium)
                            Text(text = "Fecha Límite: ${exchange.fechaLimite}", style = MaterialTheme.typography.bodyMedium)
                            Text(text = "Monto Máximo: \$${exchange.montoMaximo}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Participantes en una tarjeta
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "Participantes:", style = MaterialTheme.typography.bodyMedium)
                            exchange.participantes.forEach { participant ->
                                Text(text = "• ${participant.nombre} - ${participant.correo}", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Temas en una tarjeta
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "Temas:", style = MaterialTheme.typography.bodyMedium)
                            exchange.temas.forEach { tema ->
                                Text(text = "• $tema", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Botones con fondo y texto ajustado
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = {
                                // Lógica para editar
                                navController.navigate("edit/${exchange.id}")
                            },
                            modifier = Modifier.width(120.dp)
                        ) {
                            Text("Editar")
                        }

                        Button(
                            onClick = {
                                // Lógica para eliminar
                                db.collection("intercambios").document(exchange.id)
                                    .delete()
                                    .addOnSuccessListener {
                                        Toast.makeText(context, "Intercambio eliminado", Toast.LENGTH_SHORT).show()
                                        navController.popBackStack()
                                    }
                                    .addOnFailureListener { exception ->
                                        Toast.makeText(context, "Error al eliminar: ${exception.message}", Toast.LENGTH_SHORT).show()
                                    }
                            },
                            modifier = Modifier.width(120.dp)
                        ) {
                            Text("Eliminar")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón para realizar el sorteo con estilo mejorado
                    Button(
                        onClick = {
                            val participants = exchange.participantes.map { it.nombre }
                            if (participants.size < 2) {
                                Toast.makeText(context, "Se necesitan al menos 2 participantes", Toast.LENGTH_SHORT).show()
                            } else {
                                val shuffled = participants.shuffled()
                                val result = shuffled.zip(shuffled.drop(1) + shuffled.take(1))
                                sortedResult.value = result
                                Toast.makeText(context, "¡Sorteo realizado!", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("Realizar Sorteo", color = MaterialTheme.colorScheme.onSecondary)
                    }
// Enviar correos con la lista del sorteo
                    sortedResult.value.forEach { (giver, receiver) ->
                        val participant = exchange.participantes.find { it.nombre == giver }
                        val aux = participant?.correo ?: "dfrancohdez@gmail.com"
                        val message = buildString {
                            append("Hola $giver,\n\n")
                            append("¡Gracias por participar en el intercambio \"${exchange.nombre}\"!\n\n")
                            append("Detalles del intercambio:\n")
                            append("- Fecha: ${exchange.fechaIntercambio}\n")
                            append("- Lugar: ${exchange.lugar}\n")
                            append("- Monto Máximo: \$${exchange.montoMaximo}\n\n")
                            append("A ti te toca dar un regalo a: $receiver.\n\n")
                            append("Resultados completos del sorteo:\n")
                            sortedResult.value.forEach { (giver, receiver) ->
                                append("$giver → $receiver\n")
                            }
                            append("\n¡Nos vemos pronto!")
                        }

                        sendEmail(
                            subject = "Asignación del Intercambio: ${exchange.nombre}",
                            body = message,
                            to = aux
                        )
                    }
                    if (sortedResult.value.isNotEmpty()) {
                        Text("Resultados del Sorteo:", style = MaterialTheme.typography.bodyMedium)
                        sortedResult.value.forEach { (giver, receiver) ->
                            Text("$giver → $receiver", style = MaterialTheme.typography.bodyMedium)



                    }
                }
            }
        }
    }
}}

