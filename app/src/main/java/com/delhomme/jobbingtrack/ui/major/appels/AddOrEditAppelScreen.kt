package com.delhomme.jobbingtrack.ui.major.appels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.ui.components.forms.ReusableForm
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import com.delhomme.jobbingtrack.data.viewmodel.AppelViewModel
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntrepriseViewModel
import com.delhomme.jobbingtrack.data.viewmodel.RelanceViewModel
import com.delhomme.jobbingtrack.ui.components.forms.selectors.EntitySelectorField
import com.delhomme.jobbingtrack.utils.resolveCompanyId
import java.util.UUID


@Composable
fun AddOrEditAppelScreen(
    userId: String,
    appelId: String? = null,
    linkedCandidatureId: String? = null,
    linkedCompanyId: String? = null,
    linkedContactId: String? = null,
    linkedRelanceId: String? = null,
    vm: AppelViewModel = viewModel(),
    candVm: CandidatureViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    relanceVm: RelanceViewModel = viewModel(),
    entrepriseVm: EntrepriseViewModel = viewModel(),
    onCancel: () -> Unit
) {
    val appels    = vm.activeForUser(userId).observeAsState(emptyList()).value
    val candidats = candVm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val contacts  = contactVm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val relances  = relanceVm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val entreprises = entrepriseVm.activeForUser(userId = userId).observeAsState(emptyList()).value

    val existing = appels.find { it.id == appelId }

    val resolvedCandidatureId = existing?.candidatureId ?: linkedCandidatureId


    var selCandId by remember { mutableStateOf(existing?.candidatureId ?: linkedCandidatureId )}
    var selContactId by remember { mutableStateOf(existing?.contactId ?: linkedContactId) }
    var selRelanceId by remember { mutableStateOf(existing?.relanceId ?: linkedRelanceId) }

    val finalCompanyId = resolveCompanyId(
        existingAppel = existing,
        candidatures = candidats,
        relances = relances,
        linkedCandidatureId = selCandId,
        linkedRelanceId = selRelanceId,
        fallbackCompanyId = linkedCompanyId
    )

    val companyName = entreprises.find { it.id == finalCompanyId }?.name.orEmpty()

    // 5) Définir les champs du formulaire
    val fields = listOf(
        FormField("dateTime", "Date et heure de l'appel", FieldType.DATE, isRequired = true),
        FormField("subject",  "Objet de l'appel",               FieldType.SUGGESTION_TEXT,
            options = listOf("Appel de suivi","Prise de contact","Demande d'informations"),
            isRequired = true),
        FormField("notes", "Notes sur l'appel", FieldType.MULTILINE_TEXT)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Sélecteur de candidature (optionnel)
        if (linkedCandidatureId == null) {
            EntitySelectorField(
                label = "Candidature (opt.)",
                selectedEntityId = selCandId,
                allEntities = candidats,
                getEntityLabel = { it.title },
                onEntitySelected = { selCandId = it.id },
                allowCreation = false
            )
        }
        // Sélecteur de relance (optionnel)
        if (linkedRelanceId == null) {
            EntitySelectorField(
                label = "Relance (opt.)",
                selectedEntityId = selRelanceId,
                allEntities = relances,
                getEntityLabel = { it.notes.toString() },
                onEntitySelected = { selRelanceId = it.id },
                allowCreation = false
            )
        }

        // Affichage de l’entreprise (non éditable)
        OutlinedTextField(
            value    = companyName,
            onValueChange = {},
            label = { Text("Entreprise liée") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Contact optionnel avec création rapide
        EntitySelectorField(
            label = "Contact (optionnel)",
            selectedEntityId = selContactId,
            allEntities = contacts.filter { it.companyId == finalCompanyId },
            getEntityLabel = { "${it.firstName} ${it.lastName}" },
            onEntitySelected = { selContactId = it.id },
            allowCreation = true,
            onCreateEntity = { fullName ->
                val parts = fullName.trim().split(" ")
                val firstName = parts.firstOrNull() ?: ""
                val lastName = parts.drop(1).joinToString(" ")
                val newId = UUID.randomUUID().toString()

                val newContact = ContactEntity(
                    id = newId,
                    userId = userId,
                    firstName = firstName,
                    lastName = lastName,
                    companyId = finalCompanyId ?: "",
                    candidatureId = selCandId,
                    phone = null,
                    email = null,
                    position = null,
                    department = null,
                    notes = null,
                    syncHash = "contact-$newId"
                )
                contactVm.save(newContact)
                selContactId = newId
            }
        )

        ReusableForm(
            fields = fields,
            initialValues = existing?.let {
                mutableMapOf(
                    "dateTime" to it.dateTime.toString(),
                    "subject"  to it.subject,
                    "notes" to (it.notes         ?: "")
                )
            } ?: emptyMap(),
            onSubmit = { form ->
                val id = existing?.id ?: UUID.randomUUID().toString()
                val entity = AppelEntity(
                    id           = id,
                    subject      = form["subject"]!!,
                    companyId    = finalCompanyId ?: "",
                    contactId    = selCandId?.ifBlank { null },
                    candidatureId= selCandId,
                    relanceId    = selRelanceId?.ifBlank { null },
                    dateTime     = form["dateTime"]!!.toLong(),
                    notes        = form["notes"]?.takeIf(String::isNotBlank),
                    syncHash     = existing?.syncHash ?: "app-$id",
                    isArchived   = existing?.isArchived ?: false,
                    isDeleted    = existing?.isDeleted  ?: false,
                    userId       = userId,
                )
                vm.save(entity)
                onCancel()
            },
            onCancel = onCancel
        )
    }
}
