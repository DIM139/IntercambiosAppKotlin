package com.example.myapplicationexchange

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationexchange.pages.CreateExchangePage
import com.example.myapplicationexchange.pages.HomePage
import com.example.myapplicationexchange.pages.LoginPage
import com.example.myapplicationexchange.pages.SingPage

@Composable
fun MyAppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginPage(navController) }
        composable("signup") { SingPage(navController) }
        composable("home") { HomePage(navController) } // Página principal
        composable("create_exchange") { CreateExchangePage(navController) }
    }
}

