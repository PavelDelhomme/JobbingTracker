package com.delhomme.jobbingtrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

enum class CalendarViewType {
    DAY, WEEK, TWO_WEEKS, MONTH, PLANNING
}

@Composable
fun DrawerContent(
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onArchiveClick: () -> Unit,
    onTrashClick: () -> Unit,
    filters: Map<String, Boolean>? = null,
    onFilterChange: ((String, Boolean) -> Unit)? = null,
    onViewTypeChange: (CalendarViewType) -> Unit,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // fond blanc par défaut
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        if (filters != null && onFilterChange != null) {
            Text("Filtres d'événements", style = MaterialTheme.typography.titleMedium)
            filters.forEach { (filterName, isChecked) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onFilterChange(filterName, !isChecked) }
                        .padding(vertical = 8.dp)
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { onFilterChange(filterName, it) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(filterName)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Type de vue", style = MaterialTheme.typography.titleMedium)
            CalendarViewType.values().forEach { viewType ->
                TextButton(
                    onClick = {
                        onViewTypeChange(viewType)
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(viewType.name)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Liens de navigation
        TextButton(onClick = onProfileClick) { Text("Voir Profil") }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onSettingsClick) { Text("Paramètres") }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onArchiveClick) { Text("Archives") }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onTrashClick) { Text("Corbeille") }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onLogoutClick) { Text("Déconnexion") }
    }
}
