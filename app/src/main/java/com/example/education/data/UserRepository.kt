//package com.example.education.data
//
//object UserRepository {
//    private val users = mutableMapOf<String, String>() // email to password mapping
//
//    fun addUser(email: String, password: String): Boolean {
//        if (users.containsKey(email)) {
//            return false // User already exists
//        }
//        users[email] = password
//        return true
//    }
//
//    fun validateUser(email: String, password: String): Boolean {
//        return users[email] == password
//    }
//
//    fun isValidEmail(email: String): Boolean {
//        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
//        return email.matches(emailRegex.toRegex())
//    }
//}