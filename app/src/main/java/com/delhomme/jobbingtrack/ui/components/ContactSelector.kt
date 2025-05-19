package com.delhomme.jobbingtrack.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AssistChip
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContactSelectorField(
    label: String,
    allContacts: List<ContactEntity>,
    selectedContacts: List<ContactEntity>,
    onContactsChanged: (List<ContactEntity>) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    // on affiche les suggestions seulement quand on tape
    val filtered = allContacts
        .filter { "${it.firstName} ${it.lastName}".contains(searchText, true) }
        .filter { it !in selectedContacts }

    Column {
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                expanded   = it.isNotBlank() && filtered.isNotEmpty()
            },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded   = false
                searchText = ""    // ou seulement `expanded = false`
            }
        ) {
            filtered.take(5).forEach { contact ->
                DropdownMenuItem(
                    text = { Text("${contact.firstName} ${contact.lastName}") },
                    onClick = {
                        onContactsChanged(selectedContacts + contact)
                        searchText = ""
                        expanded   = false
                    }
                )
            }
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            selectedContacts.forEach { contact ->
                AssistChip(
                    onClick = {},
                    label = { Text("${contact.firstName} ${contact.lastName}") },
                    trailingIcon = {
                        IconButton(onClick = {
                            onContactsChanged(selectedContacts - contact)
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Supprimer")
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun MultiContactSelectorField(
    label: String,
    selectedContacts: List<Contact>,
    onAddContact: (Contact) -> Unit,
    onRemoveContact: (Contact) -> Unit
) {
    val allContacts = FakeDataProvider.contacts
    var input by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it; expanded = true },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth()
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            selectedContacts.forEach { contact ->
                AssistChip(
                    onClick = {},
                    label = { Text("${contact.firstName} ${contact.lastName}") },
                    trailingIcon = {
                        IconButton(onClick = { onRemoveContact(contact) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Supprimer")
                        }
                    }
                )
                Spacer(Modifier.width(4.dp))
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            allContacts.filter { it.firstName.contains(input, true) || it.lastName.contains(input, true) }
                .forEach { suggestion ->
                    DropdownMenuItem(
                        text = { Text("${suggestion.firstName} ${suggestion.lastName}") },
                        onClick = {
                            onAddContact(suggestion)
                            input = ""
                            expanded = false
                        }
                    )
                }
        }
    }
}
