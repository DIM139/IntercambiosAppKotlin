package com.example.myapplicationexchange

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplicationexchange.pages.CreateExchangePage
import com.example.myapplicationexchange.pages.ExchangeDetailsPage
import com.example.myapplicationexchange.pages.ExchangeListPage
import com.example.myapplicationexchange.pages.HomePage
import com.example.myapplicationexchange.pages.JoinExchangePage
import com.example.myapplicationexchange.pages.LoginPage
import com.example.myapplicationexchange.pages.SingPage

@Composable
fun MyAppNavigation(navController: NavHostController) {
    // Variable para almacenar el nombre de usuario
    val userName = remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "login") {
        // Pantalla de inicio de sesión
        composable("login") {
            LoginPage(
                navController = navController,
                onLoginSuccess = { name ->
                    // Guardamos el nombre del usuario cuando el login es exitoso
                    userName.value = name
                    // Navegamos a HomePage pasando el nombre de usuario
                    navController.navigate("home")
                }

            )

        }

        // Pantalla de inicio (Home)
        composable("home") {
            HomePage(navController = navController, userName = userName.value)
        }

        composable("sing"){
            SingPage(navController)
        }

        // Pantalla para crear intercambio
        composable("createExchange") {
            CreateExchangePage(navController = navController)
        }

        composable("viewExchanges"){
            ExchangeListPage(navController = navController)
        }

        // Pantalla para unirse a un intercambio
        composable("joinExchange") {
            JoinExchangePage(navController = navController)
        }

        composable("exchangeDetails/{exchangeId}") { backStackEntry ->
            val exchangeId = backStackEntry.arguments?.getString("exchangeId") ?: ""
            ExchangeDetailsPage(exchangeId, navController)
        }
    }
}


