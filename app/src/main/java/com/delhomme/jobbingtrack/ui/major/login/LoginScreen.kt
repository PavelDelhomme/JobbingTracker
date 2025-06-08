package com.delhomme.jobbingtrack.ui.major.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.navigation.Routes
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.delhomme.jobbingtrack.data.viewmodel.Authentication.LoginViewModel


@Composable
fun LoginScreen(
    navController: NavController? = null,
    loginViewModel: LoginViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val isLoading by loginViewModel.isLoading.observeAsState(false)
    val errorMessage by loginViewModel.errorMessage.observeAsState()
    val loginSuccess by loginViewModel.loginSuccess.observeAsState(false)

    // Dès qu’on a réussi la connexion, on va sur MAIN et on vide la backstack LOGIN
    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            navController?.navigate(Routes.MAIN) {
                popUpTo(Routes.LOGIN) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (email.isNotEmpty()) {
                    IconButton(onClick = { email = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Effacer")
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                Row {
                    // Effacer
                    if (password.isNotEmpty()) {
                        IconButton(onClick = { password = ""}) {
                            Icon(Icons.Default.Close, contentDescription = "Effacer")
                        }
                    }
                    // OEil
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        val v = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                        val cd = if (passwordVisible) "Cacher le mot de passe" else "Afficher le mot de passe"
                        Icon(v, contentDescription = cd)
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { loginViewModel.login(email, password) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Connexion..." else "Se connecter")
        }

        errorMessage?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(12.dp))
        TextButton(
            onClick = { navController?.navigate(Routes.REGISTER) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Créer un compte")
        }
    }
}
