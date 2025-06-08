package com.delhomme.jobbingtrack.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.ui.main.MainSection


@Composable
fun BottomNavigationBar(
    //selectedRoute: String,
    //onTabSelected: (String) -> Unit
    selectedSection: MainSection,
    onTabSelected: (MainSection) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            label = { Text("Dashboard") },
            selected = selectedSection == MainSection.DASHBOARD,
            onClick = { onTabSelected(MainSection.DASHBOARD) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") }
        )
        NavigationBarItem(
            label = { Text("Candidatures") },
            selected = selectedSection == MainSection.APPLICATIONS,
            onClick = { onTabSelected(MainSection.APPLICATIONS) },
            icon = { Icon(Icons.Default.List, contentDescription = "Candidatures") }
        )
        NavigationBarItem(
            label = { Text("Calendrier") },
            selected = selectedSection == MainSection.CALENDAR,
            onClick = { onTabSelected(MainSection.CALENDAR) },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendrier") }
        )
    }
}
