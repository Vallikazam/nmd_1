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
import com.example.education.questionsList // ✅ Импорт списка вопросов

@Composable
fun ForumScreen(navController: NavController) {
	var newQuestion by remember { mutableStateOf("") }

	Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
    	Text("Форум", style = MaterialTheme.typography.headlineMedium)

    	// Поле для ввода нового вопроса
    	OutlinedTextField(
        	value = newQuestion,
        	onValueChange = { newQuestion = it },
        	label = { Text("Введите ваш вопрос") },
        	modifier = Modifier.fillMaxWidth()
    	)

    	// Кнопка для публикации вопроса
    	Button(
        	onClick = {
            	if (newQuestion.isNotBlank()) {
                	questionsList.add(newQuestion) // ✅ Добавляем вопрос в список
                	newQuestion = "" // Очищаем поле
            	}
        	},
        	modifier = Modifier.padding(top = 8.dp)
    	) {
        	Text("Опубликовать вопрос")
    	}

    	// Список опубликованных вопросов
    	LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
        	items(questionsList) { question ->
            	Card(
                	modifier = Modifier
                    	.fillMaxWidth()
                    	.padding(vertical = 8.dp),
                	elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            	) {
                	Column(modifier = Modifier.padding(16.dp)) {
                    	// Заголовок вопроса (кликабелен)
                    	Text(
                        	text = question,
                        	style = MaterialTheme.typography.bodyLarge,
                        	modifier = Modifier
                            	.fillMaxWidth()
                            	.clickable { navController.navigate("answers/$question") } // ✅ Переход по клику
                    	)

                    	// Кнопка "Ответить" внутри вопроса
                    	Button(
                        	onClick = { navController.navigate("answers/$question") },
                        	modifier = Modifier.padding(top = 8.dp)
                    	) {
                        	Text("Ответить")
                    	}
                	}
            	}
        	}
    	}
	}
}
