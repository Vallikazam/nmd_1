package com.example.education

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.Modifier
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.education.ui.screens.*
import com.example.education.ui.theme.EducationalPlatformTheme

// ✅ Глобальная переменная для хранения ответов (добавлено здесь)
val answersMap = mutableStateMapOf<String, MutableList<String>>() // ✅ Глобальное хранилище ответов
val questionsList = mutableStateListOf<String>() // ✅ Глобальное хранилище вопросов

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EducationalPlatformTheme {
                val navController = rememberNavController()
                MainScreen(navController)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Educational Platform") }) },
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavigationGraph(navController)
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("forum") { ForumScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("profession/{professionId}") { backStackEntry ->
            val professionId = backStackEntry.arguments?.getString("professionId") ?: ""
            ProfessionScreen(navController, professionId)
        }
        composable("answers/{question}") { backStackEntry ->
            val question = backStackEntry.arguments?.getString("question")
            AnswersScreen(navController, question)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentDestination by navController.currentBackStackEntryAsState()
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentDestination?.destination?.route == "home",
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "Forum") },
            label = { Text("Forum") },
            selected = currentDestination?.destination?.route == "forum",
            onClick = { navController.navigate("forum") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = currentDestination?.destination?.route == "profile",
            onClick = { navController.navigate("profile") }
        )
    }
}
