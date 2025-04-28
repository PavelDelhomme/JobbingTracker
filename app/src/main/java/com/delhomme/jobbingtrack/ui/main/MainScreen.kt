package com.delhomme.jobbingtrack.ui.main

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.appels.AppelsScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidaturesTabScreen
import com.delhomme.jobbingtrack.ui.contacts.ContactsScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntreprisesScreen
import com.delhomme.jobbingtrack.ui.entretiens.EntretiensScreen
import com.delhomme.jobbingtrack.ui.relances.RelancesScreen
import com.delhomme.jobbingtrack.ui.components.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val currentScreen = remember { mutableStateOf(Routes.HOME) }
    var bottomSheetContent by remember { mutableStateOf(BottomSheetContentType.NONE) }
    var linkedCandidatureId by remember { mutableStateOf<String?>(null) }

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(
                onProfileClick = { /* TODO */ },
                onSettingsClick = { /* TODO */ },
                onLogoutClick = { /* TODO */ }
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("JobbingTrack") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigationBar(
                    selectedRoute = currentScreen.value,
                    onTabSelected = { route ->
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        bottomSheetContent = when (currentScreen.value) {
                            Routes.HOME, Routes.CANDIDATURES -> BottomSheetContentType.ADD_CANDIDATURE
                            Routes.CONTACTS -> BottomSheetContentType.ADD_CONTACT
                            Routes.ENTREPRISES -> BottomSheetContentType.ADD_ENTREPRISE
                            Routes.RELANCES -> BottomSheetContentType.ADD_RELANCE
                            Routes.ENTRETIENS -> BottomSheetContentType.ADD_ENTRETIEN
                            Routes.CALENDAR -> BottomSheetContentType.ADD_APPEL
                            else -> BottomSheetContentType.NONE
                        }
                    }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Ajouter")
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (currentScreen.value) {
                    Routes.HOME -> DashboardScreen()
                    Routes.CANDIDATURES -> CandidaturesTabScreen(
                        navController = navController,
                        candidatures = FakeDataProvider.candidatures,
                        entreprises = FakeDataProvider.entreprises,
                        relances = FakeDataProvider.relances,
                        appels = FakeDataProvider.appels,
                        contacts = FakeDataProvider.contacts,
                        entretiens = FakeDataProvider.entretiens,
                    )
                    Routes.CONTACTS -> ContactsScreen(
                        contacts = FakeDataProvider.contacts,
                        onItemClick = { /* TODO ouvrir détail contact */ },
                        onAddClick = { /* handled by FAB */ }

                    )
                    Routes.ENTREPRISES -> EntreprisesScreen(
                        entreprises = FakeDataProvider.entreprises,
                        onItemClick = { /* TODO ouvrir détail entreprise */ },
                        onAddClick = { /* handled by FAB */ }

                    )
                    Routes.RELANCES -> RelancesScreen(
                        relances = FakeDataProvider.relances,
                        onItemClick = { /* TODO ouvrir détail relance */ },
                   )
                    Routes.ENTRETIENS -> EntretiensScreen(
                        entretiens = FakeDataProvider.entretiens,
                        onItemClick = { /* TODO ouvrir détail entretien */ },
                    )
                    Routes.CALENDAR -> AppelsScreen(
                        appels = FakeDataProvider.appels,
                        onItemClick = { /* TODO ouvrir détail appel */ },
                        onAddClick = { /* handled by FAB */ }
                    )
                }
            }
        }
        BottomSheetHost(
            visibleContent = bottomSheetContent,
            linkedCandidatureId = linkedCandidatureId,
            onDismissRequest = {
                bottomSheetContent = BottomSheetContentType.NONE
                linkedCandidatureId = null
            }
        )

    }
}
