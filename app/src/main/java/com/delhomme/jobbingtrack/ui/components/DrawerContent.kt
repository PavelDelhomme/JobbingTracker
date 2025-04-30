package com.delhomme.jobbingtrack.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onArchiveClick: () -> Unit,
    onTrashClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextButton(onClick = onProfileClick) {
            Text("Voir Profil")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onSettingsClick) {
            Text("Paramètres")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onArchiveClick) {
            Text("Archives")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onTrashClick) {
            Text("Corbeille")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onLogoutClick) {
            Text("Déconnexion")
        }
    }
}
