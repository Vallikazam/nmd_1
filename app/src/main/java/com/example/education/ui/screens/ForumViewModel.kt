package com.example.education.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf

class ForumViewModel : ViewModel() {
    val questions = mutableStateListOf(
        "Как улучшить навыки программирования?",
        "Что такое Jetpack Compose?"
    )

    val answersMap = mutableStateMapOf<String, MutableList<String>>()

    fun addQuestion(question: String) {
        if (question.isNotBlank() && !questions.contains(question)) {
            questions.add(question)
            answersMap[question] = mutableListOf()
        }
    }

    fun addAnswer(question: String, answer: String) {
        if (question.isNotBlank() && answer.isNotBlank()) {
            answersMap.getOrPut(question) { mutableListOf() }.add(answer)
        }
    }

    fun getAnswers(question: String): List<String> {
        return answersMap[question] ?: emptyList()
    }
}
