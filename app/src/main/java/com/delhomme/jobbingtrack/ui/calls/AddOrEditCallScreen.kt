package com.delhomme.jobbingtrack.ui.calls

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
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.ui.dialogs.ReusableConfirmDialog
import com.delhomme.jobbingtrack.commons.ui.forms.FieldType
import com.delhomme.jobbingtrack.commons.ui.forms.FormField
import com.delhomme.jobbingtrack.commons.ui.forms.ReusableForm
import com.delhomme.jobbingtrack.commons.ui.forms.selectors.EntitySelectorField
import com.delhomme.jobbingtrack.utils.handleCompanyChange
import com.delhomme.jobbingtrack.utils.resolveCompanyId
import java.util.UUID
import kotlin.text.toLong


@Composable
fun AddOrEditCallScreen(
    userId: String,
    callId: String? = null,
    linkedApplicationId: String? = null,
    linkedCompanyId: String? = null,
    linkedContactId: String? = null,
    linkedFollowUpId: String? = null,
    vm: CallViewModel = hiltViewModel(),
    applicationVm: ApplicationViewModel = hiltViewModel(),
    contactVm: ContactViewModel = hiltViewModel(),
    followUpVm: FollowUpViewModel = hiltViewModel(),
    companyVm: CompanyViewModel = hiltViewModel(),
    onCancel: () -> Unit
) {
    val calls    = vm.activeForUser(userId).observeAsState(emptyList()).value
    val applications = applicationVm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val contacts  = contactVm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val followUps  = followUpVm.activeForUser(userId = userId).observeAsState(emptyList()).value
    val companies = companyVm.activeForUser(userId = userId).observeAsState(emptyList()).value

    val existing = calls.find { it.id == callId }

    var selectedApplicationId by remember { mutableStateOf(existing?.applicationId ?: linkedApplicationId )}
    var selectedContactId by remember { mutableStateOf(existing?.contactId ?: linkedContactId) }
    var selectedFollowUpId by remember { mutableStateOf(existing?.followUpId ?: linkedFollowUpId) }

    val finalCompanyId = resolveCompanyId(
        existingCall = existing,
        applications = applications,
        followUps = followUps,
        linkedApplicationId = selectedApplicationId,
        linkedFollowUpId = selectedFollowUpId,
        fallbackCompanyId = linkedCompanyId
    )

    val companyName = companies.find { it.id == finalCompanyId }?.name.orEmpty()

    // 5) Définir les champs du formulaire
    val fields = listOf(
        FormField("dateTime", "Date et heure de l'appel", FieldType.DATE, isRequired = true),
        FormField("subject",  "Objet de l'appel",               FieldType.SUGGESTION_TEXT,
            options = listOf("Appel de suivi","Prise de contact","Demande d'informations"),
            isRequired = true),
        FormField("notes", "Notes sur l'appel", FieldType.MULTILINE_TEXT)
    )

    var showUnlinkDialog by remember { mutableStateOf(false) }
    var pendingSubmit by remember { mutableStateOf<(() -> Unit)?>(null) }

    ReusableConfirmDialog(
        show = showUnlinkDialog,
        title = "Changer d'entreprise ?",
        message = "Cet appel sera délié de l'ancienne entreprise et rattaché à la nouvelle. Voulez-vous continuer ?",
        onConfirm = {
            showUnlinkDialog = false
            pendingSubmit?.invoke()
        },
        onDismiss = { showUnlinkDialog = false }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Sélecteur de candidature (optionnel)
        if (linkedApplicationId == null) {
            EntitySelectorField(
                label = "Candidature (opt.)",
                selectedEntityId = selectedApplicationId,
                allEntities = applications,
                getEntityLabel = { it.title },
                onEntitySelected = { selectedApplicationId = it.id },
                allowCreation = false
            )
        }
        // Sélecteur de relance (optionnel)
        if (linkedFollowUpId == null) {
            EntitySelectorField(
                label = "Relance (opt.)",
                selectedEntityId = selectedFollowUpId,
                allEntities = followUps,
                getEntityLabel = { it.notes.toString() },
                onEntitySelected = { selectedFollowUpId = it.id },
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
            selectedEntityId = selectedContactId,
            allEntities = contacts.filter { it.companyId == finalCompanyId },
            getEntityLabel = { "${it.firstName} ${it.lastName}" },
            onEntitySelected = { selectedContactId = it.id },
            allowCreation = true,
            onCreateEntity = { fullName ->
                val parts = fullName.trim().split(" ")
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
                    companyId = finalCompanyId ?: "",
                    applicationIds = selectedApplicationId?.let { listOf(it) } ?: emptyList(),
                    interviewIds = emptyList(),
                    followUpIds = selectedFollowUpId?.let { listOf(it) } ?: emptyList(),
                    callIds = listOf(callId),
                    notes = null,
                    base = CommonEntityFields(
                        userId = userId,
                        syncHash = "contact-$newId"
                    )
                )
                contactVm.save(newContact)
                selectedContactId = newId
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
                val companyChanged = existing?.companyId != finalCompanyId

                val submitAction = {
                    val oldCompany = companies.find { it.id == existing?.companyId }
                    val newCompany = companies.find { it.id == finalCompanyId }
                    handleCompanyChange(
                        oldCompany = oldCompany,
                        newCompany = newCompany,
                        entityId = id,
                        companyIdField = { it.callsIds ?: emptyList() },
                        copyWithIds = { company, newIds -> company.copy(callsIds = newIds) },
                        save = { companyVm.save(it) }
                    )
                    val entity = CallEntity(
                        id = id,
                        subject = form["subject"]!!,
                        companyId = finalCompanyId ?: "",
                        contactId = selectedContactId?.ifBlank { null },
                        applicationId = selectedApplicationId,
                        followUpId = selectedFollowUpId?.ifBlank { null },
                        dateTime = form["dateTime"]!!.toLong(),
                        notes = form["notes"]?.takeIf(String::isNotBlank),
                        base = existing?.base ?: CommonEntityFields(userId = userId, syncHash = "call-$id")
                    )
                    vm.save(entity)
                    onCancel()
                }

                if (companyChanged) {
                    pendingSubmit = submitAction
                    showUnlinkDialog = true
                } else {
                    submitAction()
                }
            },
            onCancel = onCancel
        )
    }
}
