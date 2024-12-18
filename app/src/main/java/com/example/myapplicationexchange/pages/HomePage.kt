package com.example.myapplicationexchange.pages


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplicationexchange.R // Asegúrate de tener un ícono de regalo o árbol navideño en res/drawable

@Composable
fun HomePage(navController: NavController, userName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título de la aplicación
        Text(
            text = "App de intercambios",
            color = Color.Black,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Imagen o ícono navideño
        Image(
            painter = painterResource(id = R.drawable.image), // Usa un ícono como regalo o árbol
            contentDescription = "Icono Navideño",
            modifier = Modifier
                .size(250.dp)
                .padding(bottom = 16.dp)
        )

        // Mensaje de bienvenida
        Text(
            text = "¡Bienvenido, $userName!",
            color = Color.DarkGray,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Botón para crear un intercambio
        Button(
            onClick = {
                navController.navigate("createExchange") // Navega a la pantalla de creación
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Crear Intercambio")
        }

        // Botón para visualizar los intercambios
        Button(
            onClick = {
                navController.navigate("viewExchanges") // Navega a la pantalla de visualización
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Visualizar Intercambios")
        }

        // Botón para unirse a un intercambio
        Button(
            onClick = {
                navController.navigate("joinExchange") // Navega a la pantalla para unirse a un intercambio
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Unirme a un Intercambio")
        }
    }
}

