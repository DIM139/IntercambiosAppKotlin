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
fun LoginPage(navController: NavController, onLoginSuccess: (String) -> Unit) {
    var userNameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    var errorMessage by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Iniciar Sesión", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))


// Campo de Email
        OutlinedTextField(
            value = userNameInput,
            onValueChange = { userNameInput = it },
            label = { Text("Correo Electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))


        // Campo de Contraseña
        OutlinedTextField(
            value = passwordInput,
            onValueChange = { passwordInput = it },
            label = { Text("Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

//        Button(onClick = {
//            // Simulación de autenticación exitosa
//            if (userNameInput.isNotBlank() && passwordInput.isNotBlank()) {
//                onLoginSuccess(userNameInput) // Enviar el nombre ingresado
//            }
//        }) {
//            Text(text = "Ingresar")
//        }

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

        // Mensaje de error
        errorMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            auth.signInWithEmailAndPassword(userNameInput, passwordInput)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userName = auth.currentUser?.displayName ?: "Usuario"
                        navController.navigate("home?userName=$userName")
                        onLoginSuccess(userNameInput)// Pasamos el nombre de usuario al HomePage
                    } else {
                        errorMessage = "Error: ${task.exception?.message}"
                    }
                }
        }) {
            Text("Iniciar Sesión")
        }

        // Botón para ir al Registro

        TextButton(onClick = {
            println("Navegando a SignPage")
            navController.navigate("sing")
        }) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}
