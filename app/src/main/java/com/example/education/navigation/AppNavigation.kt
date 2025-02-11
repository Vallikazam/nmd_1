//package com.example.education.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.education.ui.screens.LoginScreen
//import com.example.education.ui.screens.RegisterScreen
//import com.example.education.ui.screens.HomeScreen
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = "login"
//    ) {
//        composable("login") {
//            LoginScreen(navController)
//        }
//        composable("register") {
//            RegisterScreen(navController)
//        }
//        composable("home") {
//            HomeScreen(navController)
//        }
//    }
//}
