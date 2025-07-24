package com.delhomme.jobbingtrack.features.call.presentation.ui

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.utils.resolveCompanyId
import com.delhomme.jobbingtrack.features.application.presentation.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.call.presentation.viewmodel.CallViewModel
import com.delhomme.jobbingtrack.features.company.presentation.viewmodel.CompanyViewModel
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.contact.presentation.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.features.followup.presentation.viewmodel.FollowUpViewModel
import com.delhomme.jobbingtrack.ui.components.ReusableConfirmDialog
import com.delhomme.jobbingtrack.ui.shared.EntitySelectorField
import com.delhomme.jobbingtrack.ui.shared.FieldType
import com.delhomme.jobbingtrack.ui.shared.FormField
import com.delhomme.jobbingtrack.ui.shared.ReusableForm
import java.util.UUID
import kotlin.collections.filter
import kotlin.collections.find


@Composable
fun AddOrEditCallScreen(
    userId: String,
    call_id: String? = null,
    linked_application_id: String? = null,
    linked_company_id: String? = null,
    linked_contact_id: String? = null,
    linked_followup_id: String? = null,
    vm: CallViewModel = hiltViewModel(),
    application_vm: ApplicationViewModel = hiltViewModel(),
    contact_vm: ContactViewModel = hiltViewModel(),
    followup_vm: FollowUpViewModel = hiltViewModel(),
    company_vm: CompanyViewModel = hiltViewModel(),
    onCancel: () -> Unit
) {
    val calls    = vm.activeForUser(userId).observeAsState(emptyList()).value
    val applications = application_vm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val contacts  = contact_vm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val follow_ups  = followup_vm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val companies = company_vm.activeForUser(userId = userId).observeAsState(emptyList()).value

    val existing = calls.find { it.id == call_id }

    var selected_application_id by remember { mutableStateOf(existing?.application_id ?: linked_application_id )}
    var selected_contact_id by remember { mutableStateOf(existing?.contact_id ?: linked_contact_id) }
    var selected_follow_up_id by remember { mutableStateOf(existing?.follow_up_id ?: linked_followup_id) }

    val final_company_id = resolveCompanyId(
        existing_call = existing,
        applications = applications,
        follow_ups = follow_ups,
        linked_application_d = selected_application_id,
        linked_follow_up_id = selected_follow_up_id,
        fallback_company_id = linked_company_id
    )

    val company_name = companies.find { it.id == final_company_id }?.name.orEmpty()

    // 5) Définir les champs du formulaire
    val fields = listOf(
        FormField("timestamp", "Date et heure de l'appel", FieldType.DATE, isRequired = true),
        FormField("subject",  "Objet de l'appel",               FieldType.SUGGESTION_TEXT,
            options = listOf("Appel de suivi","Prise de contact","Demande d'informations"),
            isRequired = true),
        FormField("notes", "Notes sur l'appel", FieldType.MULTILINE_TEXT)
    )

    var show_unlink_dialog by remember { mutableStateOf(false) }
    var pending_submit by remember { mutableStateOf<(() -> Unit)?>(null) }

    ReusableConfirmDialog(
        show = show_unlink_dialog,
        title = "Changer d'entreprise ?",
        message = "Cet appel sera délié de l'ancienne entreprise et rattaché à la nouvelle. Voulez-vous continuer ?",
        onConfirm = {
            show_unlink_dialog = false
            pending_submit?.invoke()
        },
        onDismiss = { show_unlink_dialog = false }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Sélecteur de candidature (optionnel)
        if (linked_application_id == null) {
            EntitySelectorField(
                label = "Candidature (opt.)",
                selectedEntityId = selected_application_id,
                allEntities = applications,
                getEntityLabel = { it.title },
                onEntitySelected = { selected_application_id = it.id },
                allowCreation = false
            )
        }
        // Sélecteur de relance (optionnel)
        if (linked_followup_id == null) {
            EntitySelectorField(
                label = "Relance (opt.)",
                selectedEntityId = selected_follow_up_id,
                allEntities = follow_ups,
                getEntityLabel = { it.notes.toString() },
                onEntitySelected = { selected_follow_up_id = it.id },
                allowCreation = false
            )
        }

        // Affichage de l’entreprise (non éditable)
        OutlinedTextField(
            value    = company_name,
            onValueChange = {},
            label = { Text("Entreprise liée") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Contact optionnel avec création rapide
        EntitySelectorField(
            label = "Contact (optionnel)",
            selectedEntityId = selected_contact_id,
            allEntities = contacts.filter { it.companyId == final_company_id },
            getEntityLabel = { "${it.first_name} ${it.last_name}" },
            onEntitySelected = { selected_contact_id = it.id },
            allowCreation = true,
            onCreateEntity = { full_name ->
                val parts = full_name.trim().split(" ")
                val first_name = parts.firstOrNull() ?: ""
                val last_name = parts.drop(1).joinToString(" ")
                val new_id = UUID.randomUUID().toString()
                val new_contact = ContactEntity(
                    id = new_id,
                    firstName = first_name,
                    lastName = last_name,
                    phone = null,
                    email = null,
                    positionId = null,      // ou une valeur si tu veux lier à un type de poste
                    departmentId = null,    // ou une valeur si tu veux lier à un département
                    companyId = final_company_id ?: "",
                    notes = null,
                    base = CommonEntityFields(
                        userId = userId,
                        syncHash = "contact-$new_id"
                    )
                )
                contact_vm.save(new_contact)
                selected_contact_id = new_id
            }
        )

        ReusableForm(
            fields = fields,
            initialValues = existing?.let {
                mutableMapOf(
                    "timestamp" to it.timestamp.toString(),
                    "subject"  to it.subject,
                    "notes" to (it.notes         ?: "")
                )
            } ?: emptyMap(),onSubmit = { form ->
                val id = existing?.id ?: UUID.randomUUID().toString()
                val entity = CallEntity(
                    id = id,
                    subject = form["subject"]!!,
                    company_id = final_company_id ?: "",
                    contact_id = selected_contact_id?.ifBlank { null },
                    application_id = selected_application_id,
                    follow_up_id = selected_follow_up_id?.ifBlank { null },
                    timestamp = form["timestamp"]!!.toLong(),
                    notes = form["notes"]?.takeIf(String::isNotBlank),
                    base = existing?.base ?: CommonEntityFields(userId = userId, syncHash = "call-$id")
                )
                vm.save(entity)
                onCancel()
            },
            onCancel = onCancel
        )
    }
}
