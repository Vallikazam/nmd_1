package com.example.education.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class ITProfession(val id: String, val title: String, val description: String)

@Composable
fun HomeScreen(navController: NavController) {
    val professions = listOf(
        ITProfession("developer", "Разработчик", "Создает программные приложения."),
        ITProfession("qa", "Тестировщик", "Проверяет программное обеспечение."),
        ITProfession("devops", "DevOps-инженер", "Автоматизирует процессы разработки."),
        ITProfession("datascientist", "Data Scientist", "Анализирует данные и строит модели."),
        ITProfession("cybersecurity", "Кибербезопасность", "Защищает системы от атак."),
        ITProfession("uxui", "UX/UI-дизайнер", "Создает удобный интерфейс."),
        ITProfession("pm", "Проектный менеджер", "Организует процесс разработки.")
    )

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(professions) { profession ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { navController.navigate("profession/${profession.id}") },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = profession.title, style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = profession.description, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}