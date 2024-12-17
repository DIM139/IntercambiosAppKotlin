package com.example.myapplicationexchange.pages

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.example.myapplicationexchange.ui.theme.MyApplicationExchangeTheme

data class Intercambio(
    val id: String,
    val nombre: String,
    val participantes: List<Map<String, String>>,
    val temas: List<String>,
    val monto_maximo: Int,
    val fecha_intercambio: String,
    val lugar: String,
    val fecha_limite: String
)

@Composable
fun ExchangeListPage() {
    val db = FirebaseFirestore.getInstance()
    val intercambiosList = remember { mutableStateListOf<Intercambio>() }

    // Se obtiene la lista de intercambios desde Firestore
    LaunchedEffect(true) {
        db.collection("intercambios")
            .orderBy("fecha_intercambio", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("Firestore", "Listen failed.", e)
                    return@addSnapshotListener
                }
                intercambiosList.clear() // Limpiamos la lista antes de cargar los nuevos datos
                snapshot?.forEach { document ->
                    val intercambio = document.toObject(Intercambio::class.java)
                    intercambiosList.add(intercambio)
                }
            }
    }

    // Composición de la UI para mostrar la lista
    MyApplicationExchangeTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Text("Intercambios Navideños", modifier = Modifier.padding(16.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(intercambiosList.size) { index ->
                    val intercambio = intercambiosList[index]
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Nombre del Intercambio: ${intercambio.nombre}")
                        Text("Fecha de intercambio: ${intercambio.fecha_intercambio}")
                        Text("Lugar: ${intercambio.lugar}")
                        Text("Fecha límite para registrarse: ${intercambio.fecha_limite}")
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}