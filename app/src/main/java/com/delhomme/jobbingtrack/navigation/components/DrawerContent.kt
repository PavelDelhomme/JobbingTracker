package com.delhomme.jobbingtrack.navigation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.commons.entities.CalendarViewType
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    onProfile: () -> Unit,
    onSettings: () -> Unit,
    onArchive: () -> Unit,
    onTrash: () -> Unit,
    onLogout: () -> Unit,
    isCalendarScreen: Boolean,
    filters: Map<String, Boolean>? = null,
    onFilterChange: ((String, Boolean) -> Unit)? = null,
    onViewTypeChange: (CalendarViewType)->Unit,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope();

    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        // — Header —
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text("JobbingTrack", style = MaterialTheme.typography.headlineMedium)
        }
        Divider()

        if (isCalendarScreen) {
            // — Filtres / Vue (optionnel) —
            filters?.let { f ->
                Text("Filtres", Modifier.padding(16.dp), style = MaterialTheme.typography.titleSmall)
                f.forEach { (name,checked) ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable{ onFilterChange?.invoke(name, !checked) }
                            .padding(horizontal=16.dp, vertical=4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked, onCheckedChange = { onFilterChange?.invoke(name, it) })
                        Spacer(Modifier.width(8.dp))
                        Text(name)
                    }
                }
                Divider(Modifier.padding(vertical=8.dp))
                Text("Vue", Modifier.padding(16.dp), style = MaterialTheme.typography.titleSmall)
                CalendarViewType.values().forEach { vt ->
                    val scope = rememberCoroutineScope()
                    ListItem(
                        headlineContent = { Text(vt.name) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable{
                                onViewTypeChange(vt)
                                scope.launch { drawerState.close() }
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                Divider(Modifier.padding(vertical=8.dp))
            }
        }

        // — Navigation —
        DrawerItem(icon = Icons.Default.Person,
            label = "Profil",
            onClick = {
                scope.launch {
                    drawerState.close()
                    onProfile()
                }
            }
        )
        DrawerItem(icon = Icons.Default.Settings,label = "Paramètres",onClick = onSettings)
        DrawerItem(icon = Icons.Default.Archive, label = "Archives",   onClick = onArchive)
        DrawerItem(icon = Icons.Default.Delete,  label = "Corbeille",  onClick = onTrash)
        Spacer(Modifier.weight(1f))
        DrawerItem(icon = Icons.Default.Logout,  label = "Déconnexion", onClick = onLogout)
    }
}
