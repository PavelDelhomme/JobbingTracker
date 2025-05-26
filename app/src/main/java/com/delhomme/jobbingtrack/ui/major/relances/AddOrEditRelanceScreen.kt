package com.delhomme.jobbingtrack.ui.major.relances

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.ui.components.forms.ReusableForm
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.data.forms.FormSuggestions
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.RelanceViewModel
import com.delhomme.jobbingtrack.ui.components.forms.selectors.EntitySelectorField
import com.delhomme.jobbingtrack.utils.resolveCompanyId
import java.util.UUID

@Composable
fun AddOrEditRelanceScreen(
    userId: String,
    relanceId: String? = null,
    linkedCandidatureId: String? = null,
    linkedCompanyId: String? = null,
    vm: RelanceViewModel = viewModel(),
    candVm: CandidatureViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    onCancel: () -> Unit
) {
    val all = vm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val existing = all.find { it.id == relanceId }

    val candidats = candVm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val contacts  = contactVm.activeForUser(userId = userId).observeAsState(emptyList()).value

    var selCandId by remember { mutableStateOf(existing?.candidatureId ?: linkedCandidatureId) }
    var selContactId by remember { mutableStateOf(existing?.contactId ?: "") }

    val finalCompanyId = resolveCompanyId(
        existingRelance = existing,
        candidatures = candidats,
        relances = all, // car c’est une relance
        linkedCandidatureId = selCandId,
        fallbackCompanyId = linkedCompanyId
    )


    val fields = listOf(
        FormField("date", "Date de relance", FieldType.DATE, isRequired = true),
        FormField("type",           "Type",           FieldType.DROPDOWN,      isRequired = true, options = FormSuggestions.relanceTypes), // Permettre l'ajout ou la suppression de type de relance en live dans le formulaire
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
            selectedEntityId = selCandId,
            allEntities = candidats,
            getEntityLabel = { it.title },
            onEntitySelected = { selCandId = it.id }
        )

        EntitySelectorField(
            label = "Contact (opt.)",
            selectedEntityId = selContactId,
            allEntities = contacts.filter { it.companyId == existing?.companyId },
            getEntityLabel = { "${it.firstName} ${it.lastName}" },
            onEntitySelected = { selContactId = it.id },
            allowCreation = false
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
                val relance  = RelanceEntity(
                    id            = existing?.id ?: UUID.randomUUID().toString(),
                    userId        = userId,
                    date          = form["date"]!!.toLong(),
                    type          = form["type"],
                    responseStatus= form["responseStatus"],
                    notes         = form["notes"],
                    candidatureId = selCandId!!,
                    companyId     = finalCompanyId!!,
                    contactId     = selContactId.ifBlank { null },
                    syncHash      = existing?.syncHash ?: "rel-${UUID.randomUUID()}",
                    isArchived    = existing?.isArchived ?: false,
                    isDeleted     = existing?.isDeleted ?: false
                )
                vm.save(relance)
                onCancel()
            },
            onCancel = onCancel
        )
    }
}