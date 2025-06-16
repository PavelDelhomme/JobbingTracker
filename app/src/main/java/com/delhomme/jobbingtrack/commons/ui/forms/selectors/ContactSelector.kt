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
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
import com.delhomme.jobbingtrack.datas.viewmodels.ContactViewModel
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
    companyId: String,
    followUpId: String? = null, // pour lié relance si besoin
    interviewId: String? = null, // pour lié entretien si besoin
    callId: String? = null, // pour lié appel si besoin
    applicationId: String? = null // pour lié candidature si besoin
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
                            position = null,
                            department = null,
                            companyId = companyId,
                            applicationIds = applicationId?.let { listOf(it) } ?: emptyList(),
                            interviewIds = interviewId?.let { listOf(it) } ?: emptyList(),
                            followUpIds = followUpId?.let { listOf(it) } ?: emptyList(),
                            callIds = callId?.let { listOf(it) } ?: emptyList(),
                            notes = null,
                            base = CommonEntityFields(
                                userId = userId,
                                syncHash = "contact-$newId"
                            )
                        )
                        contactViewModel.save(newContact)
                        // Ajoute le contact créé à la selection
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