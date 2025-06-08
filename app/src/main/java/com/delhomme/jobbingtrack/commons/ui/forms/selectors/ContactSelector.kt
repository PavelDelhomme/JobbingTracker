package com.delhomme.jobbingtrack.commons.ui.forms.selectors


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.contacts.entities.ContactEntity
import com.delhomme.jobbingtrack.contacts.viewmodels.ContactViewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContactSelectorField(
    userId: String,
    label: String,
    contactViewModel: ContactViewModel,
    selectedContacts: List<ContactEntity>,
    onContactsChanged: (List<ContactEntity>) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val allContacts = contactViewModel.activeForUser(userId).observeAsState(emptyList()).value
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