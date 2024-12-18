

package com.example.myapplicationexchange.pages

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Exchange(
    val id: String,
    val nombre: String,
    val participantes: List<Participant>,
    val temas: List<String>,
    val montoMaximo: Int,
    val fechaIntercambio: String,
    val lugar: String,
    val fechaLimite: String
)

data class Participant(
    val nombre: String,
    val correo: String,
    val telefono: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeListPage(navController: NavController) {
    val db = Firebase.firestore
    val exchangesCollection = db.collection("intercambios")
    val exchangesState = remember { mutableStateOf<List<Exchange>>(emptyList()) }
    val context = LocalContext.current

    // Recuperar datos desde Firestore
    LaunchedEffect(Unit) {
        exchangesCollection.get()
            .addOnSuccessListener { documents ->
                val exchanges = documents.mapNotNull { doc ->
                    try {
                        Exchange(
                            id = doc.getString("id") ?: "",
                            nombre = doc.getString("nombre") ?: "",
                            participantes = (doc.get("participantes") as? List<Map<String, Any>>)?.map {
                                Participant(
                                    nombre = it["nombre"] as? String ?: "",
                                    correo = it["correo"] as? String ?: "",
                                    telefono = it["telefono"] as? String ?: ""
                                )
                            } ?: emptyList(),
                            temas = (doc.get("temas") as? List<String>) ?: emptyList(),
                            montoMaximo = doc.getDouble("monto_maximo")?.toInt() ?: 0,
                            fechaIntercambio = doc.getString("fecha_intercambio") ?: "",
                            lugar = doc.getString("lugar") ?: "",
                            fechaLimite = doc.getString("fecha_limite") ?: ""
                        )
                    } catch (e: Exception) {
                        null // Ignorar documentos mal formateados
                    }
                }
                exchangesState.value = exchanges
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Error al cargar los intercambios: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // UI
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Intercambios") },
                colors = TopAppBarDefaults.mediumTopAppBarColors()
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val exchanges = exchangesState.value
            if (exchanges.isEmpty()) {
                // Mostrar mensaje si no hay intercambios
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No hay intercambios disponibles")
                }
            } else {
                // Mostrar la lista de intercambios
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(exchanges) { exchange ->
                        ExchangeItem(exchange, navController) // Pasamos el navController a ExchangeItem
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ExchangeItem(exchange: Exchange, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Navegar a la pantalla de detalles del intercambio
                navController.navigate("exchangeDetails/${exchange.id}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = exchange.nombre,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Fecha de Intercambio: ${exchange.fechaIntercambio}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Lugar: ${exchange.lugar}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
