package com.example.myapplicationexchange


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationexchange.ui.theme.MyApplicationExchangeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationExchangeTheme {
                val navController = rememberNavController()
                val userName = "Usuario Logueado" // Reemplaza con el nombre real obtenido de FirebaseAuth
                MyAppNavigation(navController = navController)
            }
        }
    }
}