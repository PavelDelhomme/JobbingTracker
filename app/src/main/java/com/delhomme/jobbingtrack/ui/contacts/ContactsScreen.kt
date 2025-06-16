package com.delhomme.jobbingtrack.ui.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.commons.ui.lists.ListScreen


@Composable
fun ContactsScreen(
    contacts: List<ContactEntity>,
    contactVm: ContactViewModel,
    onItemClick: (ContactEntity) -> Unit,
    onEdit: (ContactEntity) -> Unit,
    onArchive: (ContactEntity) -> Unit,
    onDelete: (ContactEntity) -> Unit,
    onAddClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val sorted = contacts.sortedBy { it.lastName?.lowercase() ?: "" }
        val visible = sorted.filter { !it.base.isArchived }

        ListScreen(
            dateProvider = { null },
            titleProvider = { "${it.lastName?.uppercase() ?: ""} ${it.firstName.orEmpty()}" },
            centerInfoProvider = { it.position ?: "Pas de fonction" },
            bottomLeftInfoProvider = { it.phone ?: it.email ?: "" },
            items = visible,
            onItemClick = onItemClick,
            onEdit = { onEdit(it) },
            onArchive = { onArchive(it) },
            onDelete = { onDelete(it) },
        )

        FloatingActionButton(
            onClick = onAddClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Ajouter un contact")
        }
    }
}