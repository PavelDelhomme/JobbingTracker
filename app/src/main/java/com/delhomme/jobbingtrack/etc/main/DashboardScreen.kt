package com.delhomme.jobbingtrack.etc.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.delhomme.jobbingtrack.navigation.Routes


@Composable
fun DashboardScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenue", style = MaterialTheme.typography.headlineMedium)
    }
    BackHandler(enabled = navController.currentBackStackEntryAsState().value?.destination?.route == Routes.MAIN) {
        // Bloquer le retour SEULEMENT sur MainScreen
        // Rien à faire ici pour le désactiver
    }
}