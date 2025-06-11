package com.delhomme.jobbingtrack.followsup.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.applications.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.ui.forms.FieldType
import com.delhomme.jobbingtrack.commons.ui.forms.FormField
import com.delhomme.jobbingtrack.commons.ui.forms.FormSuggestions
import com.delhomme.jobbingtrack.commons.ui.forms.ReusableForm
import com.delhomme.jobbingtrack.commons.ui.forms.selectors.ContactSelectorField
import com.delhomme.jobbingtrack.commons.ui.forms.selectors.EntitySelectorField
import com.delhomme.jobbingtrack.contacts.entities.ContactEntity
import com.delhomme.jobbingtrack.contacts.viewmodels.ContactViewModel
import com.delhomme.jobbingtrack.followsup.entities.FollowUpEntity
import com.delhomme.jobbingtrack.followsup.viewmodels.FollowUpViewModel
import com.delhomme.jobbingtrack.utils.resolveCompanyId
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

    var selApplicationId by remember { mutableStateOf(existing?.applicationId ?: linkedApplicationId) }

    var selectedContacts by remember {
        mutableStateOf<List<ContactEntity>>(
            existing?.contactsIds
                ?.filterNotNull()
                ?.mapNotNull { contactId -> contacts.find { it.id == contactId } }
                ?: emptyList()
        )
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
        FormField("type",           "Type",           FieldType.DROPDOWN,      isRequired = true, options = FormSuggestions.followUpTypes), // Permettre l'ajout ou la suppression de type de relance en live dans le formulaire
        FormField("responseStatus", "Statut réponse (En attente, Positif, Négatif, Aucun retour)", FieldType.TEXT),
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
            onContactsChanged = { selectedContacts = it },
            companyId = finalCompanyId,
            followUpId = followUpId
        )

        ReusableForm(
            fields = fields,
            initialValues = existing?.let {
                mapOf(
                    "date"           to it.date.toString(),
                    "type"           to (it.type ?: ""),
                    "responseStatus" to (it.responseStatus ?: ""),
                    "notes"          to (it.notes ?: "")
                )
            } ?: emptyMap(),
            onSubmit = { form ->
                val id = existing?.id ?: UUID.randomUUID().toString()
                val hash = existing?.base?.syncHash ?: "followup-$id"
                val followUp  = FollowUpEntity(
                    id            = id,
                    date          = form["date"]!!.toLong(),
                    type          = form["type"],
                    responseStatus= form["responseStatus"],
                    notes         = form["notes"],
                    applicationId = selApplicationId ?: "",
                    companyId = finalCompanyId,
                    contactsIds = selectedContacts.map { it.id },
                    callsIds = existing?.callsIds ?: emptyList(),
                    base = existing?.base ?: CommonEntityFields(
                        userId = userId,
                        syncHash = hash
                    )
                )
                vm.save(followUp)
                onCancel()
            },
            onCancel = onCancel
        )
    }
}