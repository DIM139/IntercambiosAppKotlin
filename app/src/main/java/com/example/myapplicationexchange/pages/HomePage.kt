//package com.example.myapplicationexchange.pages
//
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.myapplicationexchange.R // Asegúrate de tener un ícono de regalo o árbol navideño en res/drawable

//@Composable
//fun HomePage(navController: NavController, userName: String) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        // Título de la aplicación
//        Text(
//            text = "App de intercambios",
//            color = Color.Black,
//            fontSize = 28.sp,
//            modifier = Modifier.padding(bottom = 20.dp)
//        )
//
//        // Imagen o ícono navideño
//        Image(
//            painter = painterResource(id = R.drawable.image), // Usa un ícono como regalo o árbol
//            contentDescription = "Icono Navideño",
//            modifier = Modifier
//                .size(250.dp)
//                .padding(bottom = 16.dp)
//        )
//
//        // Mensaje de bienvenida
//        Text(
//            text = "¡Bienvenido, $userName!",
//            color = Color.DarkGray,
//            fontSize = 20.sp,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        // Botón para crear un intercambio
//        Button(
//            onClick = {
//                navController.navigate("createExchange") // Navega a la pantalla de creación
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//        ) {
//            Text(text = "Crear Intercambio")
//        }
//
//        // Botón para visualizar los intercambios
//        Button(
//            onClick = {
//                navController.navigate("viewExchanges") // Navega a la pantalla de visualización
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//        ) {
//            Text(text = "Visualizar Intercambios")
//        }
//
//        // Botón para unirse a un intercambio
//        Button(
//            onClick = {
//                navController.navigate("joinExchange") // Navega a la pantalla para unirse a un intercambio
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//        ) {
//            Text(text = "Unirme a un Intercambio")
//        }
//    }
//}

package com.example.myapplicationexchange.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplicationexchange.R // Asegúrate de tener un ícono de regalo o árbol navideño en res/drawable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController, userName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Barra superior
        TopAppBar(
            title = { Text(text = "App de intercambios") },
//            backgroundColor = Color(0xFF6200EE), // Color morado
//            contentColor = Color.White,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagen o ícono navideño
            Image(
                painter = painterResource(id = R.drawable.image), // Usa un ícono como regalo o árbol
                contentDescription = "Icono Navideño",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
            )

            // Mensaje de bienvenida
            Text(
                text = "¡Bienvenido, $userName!",
                color = Color.DarkGray,
                fontSize = 22.sp,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // Botón para crear un intercambio
            Button(
                onClick = {
                    navController.navigate("createExchange") // Navega a la pantalla de creación
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3232))
            ) {
                Text(text = "Crear Intercambio", color = Color.White)
            }

            // Botón para visualizar los intercambios
            Button(
                onClick = {
                    navController.navigate("viewExchanges") // Navega a la pantalla de visualización
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3232))
            ) {
                Text(text = "Visualizar Intercambios", color = Color.White)
            }

            // Botón para unirse a un intercambio
            Button(
                onClick = {
                    navController.navigate("joinExchange") // Navega a la pantalla para unirse a un intercambio
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3232))
            ) {
                Text(text = "Unirme a un Intercambio", color = Color.White)
            }
        }
    }
}

