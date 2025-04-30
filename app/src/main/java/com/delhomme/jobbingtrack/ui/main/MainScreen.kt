package com.delhomme.jobbingtrack.ui.main

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.appels.AppelsScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidaturesScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidaturesTabScreen
import com.delhomme.jobbingtrack.ui.contacts.ContactsScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntreprisesScreen
import com.delhomme.jobbingtrack.ui.entretiens.EntretiensScreen
import com.delhomme.jobbingtrack.ui.relances.RelancesScreen
import com.delhomme.jobbingtrack.ui.components.*
import kotlinx.coroutines.launch
import androidx.activity.compose.BackHandler

enum class MainSection {
    DASHBOARD, CANDIDATURES, CALENDAR
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var bottomSheetContent by remember { mutableStateOf(BottomSheetContentType.NONE) }
    var linkedCandidatureId by remember { mutableStateOf<String?>(null) }

    var currentSection by rememberSaveable { mutableStateOf(MainSection.DASHBOARD) }

    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    BackHandler {
        // Si on est déjà dans le MAIN, on ne quitte pas
        if (navController.currentDestination?.route != Routes.MAIN) {
            navController.popBackStack()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onProfileClick = { /* TODO */ },
                onSettingsClick = { /* TODO */ },
                onLogoutClick = { /* TODO */ },
                onArchiveClick = { navController.navigate(Routes.ARCHIVES)},
                onTrashClick = { navController.navigate(Routes.TRASH)},
            )
        }
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
                /*BottomNavigationBar(
                    selectedRoute = currentRoute,
                    onTabSelected = { route ->
                        navController.navigate(route) {
                            popUpTo(Routes.HOME) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )*/
                BottomNavigationBar(
                    selectedSection = currentSection,
                    onTabSelected = { section ->
                        currentSection = section
                    }
                )
            },
            floatingActionButton = {
                val fabContentType = when (currentSection) {
                    MainSection.DASHBOARD -> BottomSheetContentType.NONE
                    MainSection.CANDIDATURES -> when (selectedTabIndex) {
                        0 -> BottomSheetContentType.ADD_CANDIDATURE
                        1 -> BottomSheetContentType.ADD_ENTREPRISE
                        3 -> BottomSheetContentType.ADD_APPEL
                        4 -> BottomSheetContentType.ADD_CONTACT
                        5 -> BottomSheetContentType.ADD_ENTRETIEN
                        else -> BottomSheetContentType.NONE
                    }
                    MainSection.CALENDAR -> BottomSheetContentType.ADD_APPEL
                }

                if (fabContentType != BottomSheetContentType.NONE) {
                    FloatingActionButton(
                        onClick = {
                            bottomSheetContent = fabContentType
                        }
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Ajouter")
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (currentSection) {
                    MainSection.DASHBOARD -> DashboardScreen()
                    MainSection.CANDIDATURES -> CandidaturesTabsContent(
                        navController = navController,
                        selectedTabIndex = selectedTabIndex,
                        onTabChange = { selectedTabIndex = it }
                    )
                    MainSection.CALENDAR -> AppelsScreen(
                        appels = FakeDataProvider.appels,
                        onItemClick = { appel ->
                            navController.navigate("${Routes.APPEL_DETAIL}/${appel.id}")
                        },
                        onAddClick = { bottomSheetContent = BottomSheetContentType.ADD_APPEL }
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