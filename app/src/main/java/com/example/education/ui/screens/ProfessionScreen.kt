package com.example.education.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfessionScreen(navController: NavController, professionId: String) {
    val professions = mapOf(
        "developer" to "Разработчик занимается созданием программных продуктов и приложений.",
        "qa" to "Тестировщик выявляет ошибки в ПО и помогает улучшить его качество.",
        "devops" to "DevOps-инженер автоматизирует процессы развертывания и поддержки ПО.",
        "datascientist" to "Data Scientist анализирует данные и строит прогнозные модели.",
        "cybersecurity" to "Специалист по кибербезопасности защищает системы от угроз.",
        "uxui" to "UX/UI-дизайнер создает удобные и интуитивно понятные интерфейсы.",
        "pm" to "Проектный менеджер организует процессы и следит за сроками разработки."
    )

    val description = professions[professionId] ?: "Профессия не найдена."

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = description, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Назад")
        }
    }
}