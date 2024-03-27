package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var showSignupScreen by remember { mutableStateOf(true) }
                var showLoginScreen by remember { mutableStateOf(false) }
                var loggedIn by remember { mutableStateOf(false) }

                if (showSignupScreen) {
                    SignupScreen(
                        onSignupSuccess = { email ->
                            // After successful signup, update states to show login screen
                            showSignupScreen = false
                            showLoginScreen = true
                        },
                        onLoginClicked = { // Provide a callback for login click
                            showSignupScreen = false
                            showLoginScreen = true
                        }
                    )
                } else if (showLoginScreen) {
                    LoginScreen(
                        onLoginClicked = { loginData, onLoginSuccess ->
                            // Here you should add your logic to verify login credentials
                            // For now, let's simulate successful login
                            onLoginSuccess()
                        },
                        onSignUpClicked = {
                            // Here, set to show signup screen
                            showSignupScreen = true
                            showLoginScreen = false
                        },
                        onLoginSuccess = {
                            // After successful login, update states to show home screen
                            loggedIn = true
                            showLoginScreen = false
                        },
                        initialEmail = "" // Set initial email if needed, empty for now
                    )
                } else if (loggedIn) {
                    HomeScreen(
                        onLogout = { // Provide a callback for logout
                            loggedIn = false
                        }
                    )
                }
            }
        }
    }
}
