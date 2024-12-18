package com.example.myapplicationexchange.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
//fun LoginPage(navController: NavController) {
fun LoginPage(navController: NavController, onLoginSuccess: (String) -> Unit) {

    val auth = FirebaseAuth.getInstance()

    // Estados para email, password y error
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Mensaje de error
        errorMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Iniciar Sesión
//        Button(onClick = {
//            auth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        navController.navigate("home") // Ir a HomePage
//                    } else {
//                        errorMessage = "Error: ${task.exception?.message}"
//                    }
//                }
//        }) {
        Button(onClick = {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userName = auth.currentUser?.displayName ?: "Usuario"
                        navController.navigate("home?userName=$userName") // Pasamos el nombre de usuario al HomePage
                    } else {
                        errorMessage = "Error: ${task.exception?.message}"
                    }
                }
        }) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para ir al Registro
//        TextButton(onClick = { navController.navigate("sign") }) {
//            Text("¿No tienes cuenta? Regístrate")
//        }
        TextButton(onClick = {
            println("Navegando a SignPage")
            navController.navigate("sing")
        }) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}