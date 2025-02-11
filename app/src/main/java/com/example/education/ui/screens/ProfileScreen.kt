package com.example.education.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.example.education.R
import java.util.regex.Pattern

@Composable
fun ProfileScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    if (currentUser == null) {
        LoginRegisterScreen(auth)
    } else {
        UserProfileScreen(auth, currentUser)
    }
}

@Composable
fun UserProfileScreen(auth: FirebaseAuth, user: FirebaseUser) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_placeholder),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome, ${user.email}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { logout(auth) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
        ) {
            Text("Logout", color = Color.White)
        }
    }
}

@Composable
fun LoginRegisterScreen(auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (validateInput(email, password)) {
                    login(auth, email, password, onError = { errorMessage = it })
                } else {
                    errorMessage = "Invalid email or password"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (validateInput(email, password)) {
                    register(auth, email, password, onError = { errorMessage = it })
                } else {
                    errorMessage = "Invalid email or password"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
    }
}

fun validateInput(email: String, password: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return emailPattern.matches(email) && password.length >= 6
}

fun login(auth: FirebaseAuth, email: String, password: String, onError: (String) -> Unit) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Login successful
            } else {
                onError(task.exception?.message ?: "Login failed")
            }
        }
}

fun register(auth: FirebaseAuth, email: String, password: String, onError: (String) -> Unit) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                user?.let {
                    saveUserToDatabase(it)
                }
            } else {
                onError(task.exception?.message ?: "Registration failed")
            }
        }
}

fun saveUserToDatabase(user: FirebaseUser) {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("users")
    val userId = user.uid

    val userMap = mapOf(
        "email" to user.email,
        "uid" to userId
    )

    usersRef.child(userId).setValue(userMap)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // User data saved successfully
            } else {
                // Handle failure
            }
        }
}

fun logout(auth: FirebaseAuth) {
    auth.signOut()
    // Navigate back to login screen or update UI
}