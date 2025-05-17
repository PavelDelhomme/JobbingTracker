package com.delhomme.jobbingtrack.ui.major.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController, registerVm: RegisterViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val done by registerVm.registerSuccess.observeAsState(false)

    LaunchedEffect(done) {
        if (done) navController.popBackStack()
    }

    Column {
        TextField(value = email, onValueChange = { email = it}, label = { Text("Email") })
        TextField(value = password, onValueChange = { password = it}, label = { Text("Mot de passe") })
        Button(onClick = { registerVm.register(email, password) }) { Text("S'inscrire") }
    }
}
