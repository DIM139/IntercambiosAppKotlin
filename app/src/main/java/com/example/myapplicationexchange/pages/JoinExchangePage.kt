package com.example.myapplicationexchange.pages



import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinExchangePage(navController: NavController) {
    val context = LocalContext.current
    val db = Firebase.firestore
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var phone by remember { mutableStateOf(TextFieldValue("")) }
    var exchangeId by remember { mutableStateOf(TextFieldValue("")) }
    val isLoading = remember { mutableStateOf(false) }

    // Función para unirse al intercambio
    fun joinExchange() {
        if (name.text.isNotEmpty() && email.text.isNotEmpty() && phone.text.isNotEmpty() && exchangeId.text.isNotEmpty()) {
            isLoading.value = true

            // Obtener el intercambio
            db.collection("intercambios").document(exchangeId.text)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val participants = document.get("participantes") as? MutableList<Map<String, Any>> ?: mutableListOf()
                        participants.add(
                            mapOf(
                                "nombre" to name.text,
                                "correo" to email.text,
                                "telefono" to phone.text
                            )
                        )

                        // Actualizar el intercambio con el nuevo participante
                        db.collection("intercambios").document(exchangeId.text)
                            .update("participantes", participants)
                            .addOnSuccessListener {
                                isLoading.value = false
                                Toast.makeText(context, "Te has unido al intercambio exitosamente", Toast.LENGTH_SHORT).show()
                                navController.popBackStack() // Volver a la vista anterior (Home)
                            }
                            .addOnFailureListener {
                                isLoading.value = false
                                Toast.makeText(context, "Error al unirse al intercambio", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        isLoading.value = false
                        Toast.makeText(context, "Intercambio no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    isLoading.value = false
                    Toast.makeText(context, "Error al cargar el intercambio", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    // UI
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Unirse al Intercambio") },
                colors = TopAppBarDefaults.mediumTopAppBarColors()
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = exchangeId,
                    onValueChange = { exchangeId = it },
                    label = { Text("ID del Intercambio") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo Electrónico") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )

                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )

                Button(
                    onClick = { joinExchange() },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    enabled = !isLoading.value
                ) {
                    Text(text = if (isLoading.value) "Cargando..." else "Unirse")
                }
            }
        }
    }
}
