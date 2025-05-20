package com.delhomme.jobbingtrack.ui.major.contacts

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import com.delhomme.jobbingtrack.ui.components.ListScreen

@Composable
fun ContactsScreen(
    contacts: List<ContactEntity>,
    onItemClick: (Contact) -> Unit,
    onAddClick: (Contact) -> Unit
) {
    val sortedContacts = contacts.sortedBy { it.lastName.lowercase() }
    val visibleContacts = sortedContacts.filter { !it.isArchived }

    ListScreen(
        dateProvider = { null },
        titleProvider = { "${it.lastName.uppercase()} ${it.firstName}" },
        centerInfoProvider = { it.position ?: "Pas de fonction" },
        bottomLeftInfoProvider = { it.phone ?: it.email ?: "" },
        items = visibleContacts,
        onItemClick = onItemClick
    )
}
