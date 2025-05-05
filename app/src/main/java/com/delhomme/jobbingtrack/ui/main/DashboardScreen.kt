package com.delhomme.jobbingtrack.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
        Text(text = "Bienvenue sur JobbingTrack 🎯", style = MaterialTheme.typography.headlineMedium)
    }
    BackHandler(enabled = navController.currentBackStackEntryAsState().value?.destination?.route == Routes.MAIN) {
        // Bloquer le retour SEULEMENT sur MainScreen
        // Rien à faire ici pour le désactiver
    }
}