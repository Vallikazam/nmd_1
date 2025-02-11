package com.example.education.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ForumScreen(navController: NavController) {
    var newQuestion by remember { mutableStateOf("") }
    val questions = remember { mutableStateListOf("Как улучшить навыки программирования?", "Что такое Jetpack Compose?") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Форум", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = newQuestion,
            onValueChange = { newQuestion = it },
            label = { Text("Введите ваш вопрос") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (newQuestion.isNotBlank()) {
                    questions.add(newQuestion)
                    newQuestion = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Опубликовать вопрос")
        }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
            items(questions) { question ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = question, style = MaterialTheme.typography.bodyLarge)
                        Button(onClick = { /* TODO: Переход к ответам */ }, modifier = Modifier.padding(top = 8.dp)) {
                            Text("Ответить")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForumScreen() {
    ForumScreen(navController = rememberNavController())
}