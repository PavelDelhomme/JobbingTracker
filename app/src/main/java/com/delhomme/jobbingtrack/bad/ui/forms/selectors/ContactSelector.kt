package com.delhomme.jobbingtrack.bad.ui.forms.selectors


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
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.contacts.ContactEntity
import com.delhomme.jobbingtrack.contacts.vms.ContactViewModel
import java.util.UUID
import kotlin.collections.filter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContactSelectorField(
    userId: String,
    label: String,
    contactViewModel: ContactViewModel,
    selectedContacts: List<ContactEntity>,
    onContactsChanged: (List<ContactEntity>) -> Unit,
    companyId: String
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
                searchText = ""
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

            // Proposer la création si aucun résultat
            if (searchText.isNotBlank() && filtered.none { "${it.firstName} ${it.lastName}".equals(searchText, true) }) {
                DropdownMenuItem(
                    text = { Text("Ajouter un nouveau contact : $searchText") },
                    onClick = {
                        val parts = searchText.trim().split(" ")
                        val firstName = parts.firstOrNull() ?: ""
                        val lastName = parts.drop(1).joinToString(" ")
                        val newId = UUID.randomUUID().toString()
                        val newContact = ContactEntity(
                            id = newId,
                            firstName = firstName,
                            lastName = lastName,
                            phone = null,
                            email = null,
                            positionId = null,
                            departmentId = null,
                            companyId = companyId,
                            notes = null,
                            base = CommonEntityFields(
                                userId = userId,
                                syncHash = "contact-$newId"
                            )
                        )
                        contactViewModel.save(newContact)
                        onContactsChanged(selectedContacts + newContact)
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
