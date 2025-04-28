package com.delhomme.jobbingtrack.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.navigation.Routes

@Composable
fun BottomNavigationBar(
    selectedRoute: String,
    onTabSelected: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            label = { Text("Dashboard") },
            selected = selectedRoute == Routes.HOME,
            onClick = { onTabSelected(Routes.HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") }
        )
        NavigationBarItem(
            label = { Text("Candidatures") },
            selected = selectedRoute == Routes.CANDIDATURES,
            onClick = { onTabSelected(Routes.CANDIDATURES) },
            icon = { Icon(Icons.Default.List, contentDescription = "Candidatures") }
        )
        NavigationBarItem(
            label = { Text("Calendrier") },
            selected = selectedRoute == Routes.CALENDAR,
            onClick = { onTabSelected(Routes.CALENDAR) },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendrier") }
        )
    }
}
