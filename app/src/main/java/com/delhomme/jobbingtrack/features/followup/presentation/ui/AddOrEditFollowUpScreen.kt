package com.delhomme.jobbingtrack.features.followup.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.utils.resolveCompanyId
import com.delhomme.jobbingtrack.features.application.presentation.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.contact.presentation.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.followup.presentation.viewmodel.FollowUpViewModel
import com.delhomme.jobbingtrack.ui.components.ContactSelectorField
import com.delhomme.jobbingtrack.ui.shared.EntitySelectorField
import com.delhomme.jobbingtrack.ui.shared.FieldType
import com.delhomme.jobbingtrack.ui.shared.FormField
import com.delhomme.jobbingtrack.ui.shared.ReusableForm
import java.util.UUID


@Composable
fun AddOrEditFollowUpScreen(
    userId: String,
    followUpId: String? = null,
    linkedApplicationId: String? = null,
    linkedCompanyId: String? = null,
    vm: FollowUpViewModel = hiltViewModel(),
    applicationVm: ApplicationViewModel = hiltViewModel(),
    contactVm: ContactViewModel = hiltViewModel(),
    onCancel: () -> Unit
) {
    val all = vm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val existing = all.find { it.id == followUpId }

    val applications = applicationVm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val contacts  = contactVm.activeForUser(userId = userId).observeAsState(emptyList()).value

    // Variables d'état pour les sélections
    var selApplicationId by remember { mutableStateOf(existing?.applicationId ?: linkedApplicationId) }
    var selectedPlatformId by remember { mutableStateOf(existing?.platformId) }
    var selectedTypeId by remember { mutableStateOf(existing?.typeId) }
    var selectedResponseId by remember { mutableStateOf(existing?.responseId) }
    var selectedStatusId by remember { mutableStateOf(existing?.statusId) }

    var selectedContacts by remember { mutableStateOf<List<ContactEntity>>(emptyList()) }
    val selectedContactIds = remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(existing) {
        existing?.let {
            vm.getContactIdsForFollowUp(it.id).let { ids ->
                selectedContactIds.value = ids
                selectedContacts = contacts.filter { c -> ids.contains(c.id) }
            }
        }
    }

    val finalCompanyId = resolveCompanyId(
        existingFollowUp = existing,
        applications = applications,
        followUps = all,
        linkedApplicationId = selApplicationId,
        fallbackCompanyId = linkedCompanyId
    ) ?: ""


    val fields = listOf(
        FormField("date", "Date de relance", FieldType.DATE, isRequired = true),
        FormField("notes", "Notes", FieldType.MULTILINE_TEXT)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        EntitySelectorField(
            label = "Candidature liée",
            selectedEntityId = selApplicationId,
            allEntities = applications,
            getEntityLabel = { it.title },
            onEntitySelected = { selApplicationId = it.id }
        )

        // Multi-sélecteur avec création à la volée

        ContactSelectorField(
            userId = userId,
            label = "Contacts",
            contactViewModel = contactVm,
            selectedContacts = selectedContacts,
            onContactsChanged = {
                selectedContacts = it
                selectedContactIds.value = it.map { c -> c.id }
            },
            companyId = finalCompanyId,
        )

        EntitySelectorField(
            label = "Type de relance",
            selectedEntityId = existing?.typeId,
            allEntities = vm.getAllFollowUpTypes().observeAsState(emptyList()).value,
            getEntityLabel = { it.label },
            onEntitySelected = { selectedTypeId = it.id }
        )

        // Sélecteur pour le statut de réponse (FK vers FollowUpResponseEntity)
        EntitySelectorField(
            label = "Statut de suivi",
            selectedEntityId = existing?.responseId,
            allEntities = vm.getAllFollowUpResponses().observeAsState(emptyList()).value,
            getEntityLabel = { it.label },
            onEntitySelected = { selectedResponseId = it.id }
        )

        ReusableForm(
            fields = fields,
            initialValues = existing?.let {
                mapOf(
                    "date"           to it.date.toString(),
                    "notes"          to (it.notes ?: "")
                )
            } ?: emptyMap(),
            onSubmit = { form ->
                val id = existing?.id ?: UUID.randomUUID().toString()
                val hash = existing?.base?.syncHash ?: "followup-$id"
                val followUp = FollowUpEntity(
                    id = id,
                    date = form["date"]!!.toLong(),
                    notes = form["notes"],
                    applicationId = selApplicationId ?: "",
                    companyId = finalCompanyId,
                    platformId = selectedPlatformId ?: "",
                    typeId = selectedTypeId ?: existing?.typeId ?: "Inconnu",
                    responseId = selectedResponseId ?: existing?.responseId,
                    statusId = selectedStatusId ?: existing?.statusId,
                    base = existing?.base ?: CommonEntityFields(
                        userId = userId,
                        syncHash = hash
                    )
                )

                vm.save(followUp, selectedContactIds.value)
                onCancel()
            },
            onCancel = onCancel
        )
    }
}