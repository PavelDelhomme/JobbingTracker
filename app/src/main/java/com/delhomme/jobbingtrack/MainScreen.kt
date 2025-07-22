package com.delhomme.jobbingtrack

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.delhomme.jobbingtrack.navigation.BottomNavBar
import com.delhomme.jobbingtrack.navigation.NavGraph
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.navigation.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(userId: String) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                currentRoute = currentRoute
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                currentRoute = currentRoute
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavGraph(
                navController = navController,
                isLoggedIn = true,
                userId = userId
            )
        }
    }
}