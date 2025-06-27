package com.delhomme.jobbingtrack.ui.main

import android.widget.Toast
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.delhomme.jobbingtrack.navigation.Routes
import kotlinx.coroutines.launch
import androidx.activity.compose.BackHandler
import androidx.compose.ui.platform.LocalContext
import com.delhomme.jobbingtrack.MainActivity
import com.delhomme.jobbingtrack.commons.entities.BottomSheetContentType
import com.delhomme.jobbingtrack.commons.ui.forms.BottomSheetHost
import com.delhomme.jobbingtrack.events.CalendarViewType
import com.delhomme.jobbingtrack.ui.events.CalendarScreenContent
import com.delhomme.jobbingtrack.navigation.components.AppDrawer
import com.delhomme.jobbingtrack.navigation.components.BottomNavigationBar
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

enum class MainSection {
    DASHBOARD, APPLICATIONS, CALENDAR
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, userId: String) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var bottomSheetContent by remember { mutableStateOf(BottomSheetContentType.NONE) }
    var linkedApplicationId by remember { mutableStateOf<String?>(null) }

    var currentSection by rememberSaveable { mutableStateOf(MainSection.DASHBOARD) }

    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    val selectedDate = remember { mutableStateOf(LocalDate.now()) }

    var calendarViewType by rememberSaveable { mutableStateOf(CalendarViewType.DAY) }

    /*BackHandler(enabled = navController.currentBackStackEntryAsState().value?.destination?.route == Routes.MAIN) {
        // Bloquer le retour SEULEMENT sur MainScreen
        // Rien à faire ici pour le désactiver
    }*/

    /*BackHandler {
        if (currentSection != MainSection.DASHBOARD) {
            currentSection = MainSection.DASHBOARD
        } else {
            // Afficher un toast pour "Appuyez encore pour quitter"
        }
    }*/

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val backPressCount = remember { mutableStateOf(0) }
    val context = LocalContext.current
    val backPressTimeout = 2000L // 2 Secondes

    LaunchedEffect(backPressCount.value) {
        if (backPressCount.value > 0) {
            delay(backPressTimeout)
            backPressCount.value = 0
        }
    }

    BackHandler(enabled = currentRoute == Routes.MAIN) {
        when (currentSection) {
            MainSection.CALENDAR,
            MainSection.APPLICATIONS -> {
                currentSection = MainSection.DASHBOARD
            }
            MainSection.DASHBOARD -> {
                if (backPressCount.value < 2) {
                    backPressCount.value++
                    Toast.makeText(
                        context,
                        "Appuyez ${3 - backPressCount.value} fois pour quitter l'app",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    (context as? MainActivity)?.finish()
                }
            }
        }
    }

    val filterStates = remember {
        mutableStateMapOf(
            "Relances" to true,
            "Candidatures" to true,
            "Entretiens" to true,
            "Appels" to true
        )
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                onProfile = {
                    navController.navigate(Routes.PROFILE)
                    coroutineScope.launch { drawerState.close() }
                },
                onSettings = {
                    navController.navigate(Routes.SETTINGS)
                    coroutineScope.launch { drawerState.close() }
                },
                onArchive = {
                    navController.navigate(Routes.ARCHIVES)
                    coroutineScope.launch { drawerState.close() }
                },
                onTrash = {
                    navController.navigate(Routes.TRASH)
                    coroutineScope.launch { drawerState.close() }
                },
                onLogout = {
                    navController.navigate(Routes.LOGIN)
                    coroutineScope.launch { drawerState.close() }
                },
                filters = filterStates,
                onFilterChange = { name, checked ->
                    filterStates[name] = checked
                },
                onViewTypeChange = { calendarViewType = it },
                drawerState = drawerState,
                isCalendarScreen = currentSection == MainSection.CALENDAR
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            if (currentSection == MainSection.CALENDAR)
                                "Vue Calendrier – ${selectedDate.value}"
                            else
                                LocalDate.now().format(DateTimeFormatter.ofPattern("EEE dd MMM", Locale.FRANCE))
                        )
                    },
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
                    MainSection.APPLICATIONS -> when (selectedTabIndex) {
                        0 -> BottomSheetContentType.ADD_APPLICATION
                        1 -> BottomSheetContentType.ADD_COMPANY
                        3 -> BottomSheetContentType.ADD_CALL
                        4 -> BottomSheetContentType.ADD_CONTACT
                        5 -> BottomSheetContentType.ADD_INTERVIEW
                        else -> BottomSheetContentType.NONE
                    }
                    MainSection.CALENDAR -> BottomSheetContentType.NONE
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
                    MainSection.DASHBOARD -> DashboardScreen(
                        navController = navController,
                    )
                    MainSection.APPLICATIONS -> ApplicationsTabsContent(
                        navController = navController,
                        selectedTabIndex = selectedTabIndex,
                        onTabChange = { selectedTabIndex = it },
                        userId = userId
                    )

                    MainSection.CALENDAR -> CalendarScreenContent(
                        currentDate = selectedDate.value,
                        filterStates = filterStates,
                        onFilterChange = { name, checked -> filterStates[name] = checked },
                        userId = userId,
                        calendarViewType = calendarViewType,
                        onDateSelected = { date, newViewType ->
                            selectedDate.value = date
                            if (newViewType != null) calendarViewType = newViewType
                        }
                    )
                }
            }
        }

        BottomSheetHost(
            navController = navController,
            visibleContent = bottomSheetContent,
            linkedCandidatureId = linkedApplicationId,
            userId = userId,
            onDismissRequest = {
                bottomSheetContent = BottomSheetContentType.NONE
                linkedApplicationId = null
            }
        )

    }
}