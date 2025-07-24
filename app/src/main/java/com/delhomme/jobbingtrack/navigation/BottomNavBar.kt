package com.delhomme.jobbingtrack.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navController: NavController,
    currentRoute: String?
) {
    TopAppBar(
        title = {
            Text(
                text = when {
                    currentRoute?.startsWith(Routes.APPLICATION_DETAIL) == true -> "Détails de la candidature"
                    currentRoute?.startsWith(Routes.CONTACT_DETAIL) == true -> "Détails du contact"
                    currentRoute?.startsWith(Routes.COMPANY_DETAIL) == true -> "Détails de l'entreprise"
                    currentRoute?.startsWith(Routes.FOLLOWUP_DETAIL) == true -> "Détails de la relance"
                    currentRoute?.startsWith(Routes.CALL_DETAIL) == true -> "Détails de l'appel"
                    currentRoute?.startsWith(Routes.INTERVIEW_DETAIL) == true -> "Détails de l'entretien"
                    currentRoute == Routes.ARCHIVES -> "Archives"
                    currentRoute == Routes.TRASH -> "Corbeille"
                    currentRoute == Routes.SETTINGS -> "Paramètres"
                    currentRoute == Routes.PROFILE -> "Profil"
                    else -> "JobbingTrack"
                }
            )
        },
        navigationIcon = {
            if (navController.previousBackStackEntry != null &&
                currentRoute != Routes.MAIN) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Retour"
                    )
                }
            }
        }
    )
}

@Composable
fun BottomNavBar(
    navController: NavController,
    currentRoute: String?
) {
    val items = listOf(
        BottomNavItem(
            route = Routes.MAIN,
            icon = Icons.Default.Home,
            label = "Accueil"
        ),
        BottomNavItem(
            route = Routes.SEARCH,
            icon = Icons.Default.Search,
            label = "Recherche"
        ),
        BottomNavItem(
            route = Routes.ADD,
            icon = Icons.Default.Add,
            label = "Ajouter"
        ),
        BottomNavItem(
            route = Routes.PROFILE,
            icon = Icons.Default.Person,
            label = "Profil"
        ),
        BottomNavItem(
            route = Routes.SETTINGS,
            icon = Icons.Default.Settings,
            label = "Paramètres"
        )
    )

    if (currentRoute == Routes.MAIN ||
        currentRoute == Routes.SEARCH ||
        currentRoute == Routes.ADD ||
        currentRoute == Routes.PROFILE ||
        currentRoute == Routes.SETTINGS) {
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            popUpTo(Routes.MAIN) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

data class BottomNavItem(
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val label: String
)