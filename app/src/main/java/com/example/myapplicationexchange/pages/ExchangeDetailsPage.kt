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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplicationexchange.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ExchangeDetailsPage(exchangeId: String, navController: NavController) {
//    val db = Firebase.firestore
//    val exchangeState = remember { mutableStateOf<Exchange?>(null) }
//    val context = LocalContext.current
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
//                        temas = (document.get("temas") as? List<String>) ?: emptyList(),
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
//                    modifier = Modifier.padding(16.dp)
//                ) {
//                    Text(text = "Nombre: ${exchange.nombre}", style = MaterialTheme.typography.titleMedium)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(text = "Fecha de Intercambio: ${exchange.fechaIntercambio}", style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "Lugar: ${exchange.lugar}", style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "Fecha Límite: ${exchange.fechaLimite}", style = MaterialTheme.typography.bodyMedium)
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // Aquí puedes agregar más detalles o participantes, si los necesitas
//                    Text(text = "Participantes:", style = MaterialTheme.typography.bodyMedium)
//                    exchange.participantes.forEach { participant ->
//                        Text(text = "• ${participant.nombre} - ${participant.correo}", style = MaterialTheme.typography.bodyMedium)
//                    }
//                }
//            }
//        }
//    }
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ExchangeDetailsPage(exchangeId: String, navController: NavController) {
//    val db = Firebase.firestore
//    val exchangeState = remember { mutableStateOf<Exchange?>(null) }
//    val context = LocalContext.current
//
//    // Recuperar detalles del intercambio desde Firestore
//    LaunchedEffect(exchangeId) {
//        db.collection("intercambios").document(exchangeId)
//            .get()
//            .addOnSuccessListener { document ->
//                val exchange = try {
//                    // Recuperar los datos de Firestore y asegurarse de que cada campo esté bien procesado
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
//                        temas = (document.get("temas") as? List<String>) ?: emptyList(),
//                        montoMaximo = document.getLong("monto_maximo")?.toInt() ?: 0,
//                        fechaIntercambio = document.getString("fecha_intercambio") ?: "Fecha no disponible",  // Campo de tipo String
//                        lugar = document.getString("lugar") ?: "",
//                        fechaLimite = document.getString("fecha_limite") ?: "Fecha no disponible"  // Campo de tipo String
//                    )
//                } catch (e: Exception) {
//                    // Agregar manejo de errores si ocurre alguna excepción
//                    null
//                }
//                exchangeState.value = exchange
//            }
//            .addOnFailureListener { exception ->
//                // Error al obtener el documento
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
//                    modifier = Modifier.padding(16.dp)
//                ) {
//                    // Mostrar datos básicos del intercambio
//                    Text(text = "Nombre: ${exchange.nombre}", style = MaterialTheme.typography.titleMedium)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(text = "Fecha de Intercambio: ${exchange.fechaIntercambio}", style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "Lugar: ${exchange.lugar}", style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "Fecha Límite: ${exchange.fechaLimite}", style = MaterialTheme.typography.bodyMedium)
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // Mostrar participantes
//                    Text(text = "Participantes:", style = MaterialTheme.typography.bodyMedium)
//                    exchange.participantes.forEach { participant ->
//                        Text(text = "• ${participant.nombre} - ${participant.correo}", style = MaterialTheme.typography.bodyMedium)
//                    }
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // Mostrar temas
//                    Text(text = "Temas:", style = MaterialTheme.typography.bodyMedium)
//                    exchange.temas.forEach { tema ->
//                        Text(text = "• $tema", style = MaterialTheme.typography.bodyMedium)
//                    }
//                }
//            }
//        }
//    }
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ExchangeDetailsPage(exchangeId: String, navController: NavController) {
//    val db = Firebase.firestore
//    val exchangeState = remember { mutableStateOf<Exchange?>(null) }
//    val context = LocalContext.current
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
//
//                        temas = (document.get("temas") as? List<String>) ?: emptyList(),
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
//                        painter = painterResource(id = R.drawable.ic_gift), // Asegúrate de tener la imagen en res/drawable
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
//                                // Lógica para editar
//                            },
//                            modifier = Modifier.width(120.dp)
//                        ) {
//                            Text("Editar")
//                        }
//
//                        Button(
//                            onClick = {
//                                // Lógica para eliminar
//                            },
//                            modifier = Modifier.width(120.dp)
//                        ) {
//                            Text("Eliminar")
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeDetailsPage(exchangeId: String, navController: NavController) {
    val db = Firebase.firestore
    val exchangeState = remember { mutableStateOf<Exchange?>(null) }
    val context = LocalContext.current

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
                        temas = (document.get("temas") as? List<String>) ?: emptyList(), // Aquí obtenemos los temas
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
                    // Imagen representativa
                    Image(
                        painter = painterResource(id = R.drawable.ar_image), // Asegúrate de tener la imagen en res/drawable
                        contentDescription = "Intercambio",
                        modifier = Modifier.size(120.dp)
                    )

                    // Título de la aplicación
                    Text(
                        text = exchange.nombre,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    // Detalles del intercambio
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "Fecha de Intercambio: ${exchange.fechaIntercambio}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Lugar: ${exchange.lugar}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Fecha Límite: ${exchange.fechaLimite}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Monto Máximo: \$${exchange.montoMaximo}", style = MaterialTheme.typography.bodyMedium)

                        Spacer(modifier = Modifier.height(16.dp))

                        // Participantes
                        Text(text = "Participantes:", style = MaterialTheme.typography.bodyMedium)
                        exchange.participantes.forEach { participant ->
                            Text(text = "• ${participant.nombre} - ${participant.correo}", style = MaterialTheme.typography.bodyMedium)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Temas
                        Text(text = "Temas:", style = MaterialTheme.typography.bodyMedium)
                        exchange.temas.forEach { tema ->
                            Text(text = "• $tema", style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Botones: Editar y Eliminar
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = {
                                // Lógica para editar
                            },
                            modifier = Modifier.width(120.dp)
                        ) {
                            Text("Editar")
                        }

                        Button(
                            onClick = {
                                // Lógica para eliminar
                            },
                            modifier = Modifier.width(120.dp)
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}
