package com.example.education.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.education.answersMap // ✅ Импортируем глобальный answersMap

@Composable
fun AnswersScreen(navController: NavController, question: String?) {
    if (question == null) return

    val answers = answersMap.getOrPut(question) { mutableListOf() }
    var newAnswer by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Ответы", style = MaterialTheme.typography.headlineMedium)
        Text("Вопрос: $question", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 8.dp))

        OutlinedTextField(
            value = newAnswer,
            onValueChange = { newAnswer = it },
            label = { Text("Введите ваш ответ") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (newAnswer.isNotBlank()) {
                    answers.add(newAnswer)
                    newAnswer = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Опубликовать ответ")
        }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
            items(answers) { answer ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(text = answer, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
