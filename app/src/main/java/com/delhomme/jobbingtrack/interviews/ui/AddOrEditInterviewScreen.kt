package com.delhomme.jobbingtrack.interviews.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.delhomme.jobbingtrack.commons.ui.forms.FormSuggestions
import com.delhomme.jobbingtrack.commons.ui.forms.ModernDateTimePickerField
import com.delhomme.jobbingtrack.commons.ui.forms.ReusableForm
import com.delhomme.jobbingtrack.commons.ui.forms.selectors.ContactSelectorField
import com.delhomme.jobbingtrack.commons.ui.forms.selectors.EntitySelectorField
import com.delhomme.jobbingtrack.datas.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.CompanyViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.ContactViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.InterviewStyleViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.InterviewTypeViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.InterviewViewModel
import com.delhomme.jobbingtrack.utils.handleCompanyChange
import com.delhomme.jobbingtrack.utils.resolveCompanyId
import java.util.UUID
import kotlin.text.toBoolean
import kotlin.text.toIntOrNull
import kotlin.text.toLongOrNull


@SuppressLint("UnrememberedMutableState")
@Composable
fun AddOrEditInterviewScreen(
    interviewId: String?,
    linkedApplicationId: String? = null,
    linkedCompanyId: String? = null,
    onCancel: () -> Unit,
    interviewVm: InterviewViewModel = hiltViewModel(),
    interviewTypeVm: InterviewTypeViewModel = hiltViewModel(),
    interviewStyleVm: InterviewStyleViewModel = hiltViewModel(),
    applicationVm: ApplicationViewModel = hiltViewModel(),
    companyVm: CompanyViewModel = hiltViewModel(),
    contactVm: ContactViewModel = hiltViewModel(),
    userId: String
) {

    val interviews by interviewVm.allForUser(userId).observeAsState(emptyList())
    val applications by applicationVm.allForUser(userId).observeAsState(emptyList())
    val companies by companyVm.allForUser(userId).observeAsState(emptyList())
    val contacts by contactVm.allForUser(userId).observeAsState(emptyList())
    val allTypes by interviewTypeVm.all.observeAsState(emptyList())
    val allStyles by interviewStyleVm.all.observeAsState(emptyList())
    val existing = interviews.find { it.interview.id == interviewId }
    val selInitialContacts = existing?.interview?.contactsIds
        ?.filterNotNull()
        ?.mapNotNull { contactId -> contacts.find { it.id == contactId } }
        ?: emptyList()

    val selDateTime = existing?.interview?.dateTime ?: System.currentTimeMillis()

    var selectionnedApplicationId by remember { mutableStateOf(existing?.interview?.applicationId ?: linkedApplicationId ?: "") }
    var selectionnedCompanyId by remember { mutableStateOf(existing?.interview?.companyId ?: linkedCompanyId ?: "") }
    var selectionnedContacts by remember { mutableStateOf(selInitialContacts) }
    var dateTime by remember { mutableStateOf(selDateTime) }

    val finalCompanyId = resolveCompanyId(
        existingInterview = existing?.interview,
        applications = applications,
        followUps = emptyList(),
        linkedApplicationId = selectionnedApplicationId,
        fallbackCompanyId = selectionnedCompanyId
    )

    var showUnlinkDialog by remember { mutableStateOf(false) }
    var pendingSubmit by remember { mutableStateOf<(() -> Unit)?>(null) }

    // 5) Vos champs de formulaire
    val fields = listOf(
        FormField("location", "Lieu de l'entretien", FieldType.TEXT),
        FormField("style", "Style d'entretien", FieldType.DROPDOWN, isRequired = true, options = FormSuggestions.interviewStyles),
        FormField("type", "Type d'entretien", FieldType.DROPDOWN, isRequired = true, options = FormSuggestions.interviewTypes),
        FormField("preInterviewNotes", "Notes avant entretien", FieldType.MULTILINE_TEXT),
        FormField("interviewNotes", "Notes pendant entretien", FieldType.MULTILINE_TEXT),
        FormField("postInterviewNotes", "Notes après entretien", FieldType.MULTILINE_TEXT),
        FormField("returnDate", "Date de retour attendu", FieldType.DATE),
        FormField("testsNeeded", "Tests requis ?", FieldType.BOOLEAN),
        FormField("testsDeadline", "Date limite pour les tests", FieldType.DATE),
        FormField("durationMinutes", "Durée (min)", FieldType.NUMBER)
    )

    ReusableConfirmDialog(
        show = showUnlinkDialog,
        title = "Changer d'entreprise ?",
        message = "Cet entretien sera délié de l'ancienne entreprise et rattaché à la nouvelle. Voulez-vous continuer ?",
        onConfirm = {
            showUnlinkDialog = false
            pendingSubmit?.invoke()
        },
        onDismiss = { showUnlinkDialog = false }
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Date & heure
        Modifier.ModernDateTimePickerField(
            label = "Date & heure",
            initialMillis = dateTime
        ) { dateTime = it }

        EntitySelectorField(
            label = "Candidature",
            selectedEntityId = selectionnedApplicationId,
            allEntities = applications,
            getEntityLabel = { it.title },
            onEntitySelected = { selectionnedApplicationId = it.id },
            allowCreation = false
        )

        // Si pas de candidature, choisir directement l'entreprise
        if (selectionnedApplicationId.isBlank()) {
            EntitySelectorField(
                label = "Entreprise",
                selectedEntityId = selectionnedCompanyId,
                allEntities = companies,
                getEntityLabel = { it.name },
                onEntitySelected = { selectionnedCompanyId = it.id },
                allowCreation = false
            )
        }

        // Affichage non-éditable de l’entreprise
        OutlinedTextField(
            value    = companies.firstOrNull { it.id == finalCompanyId }?.name.orEmpty(),
            onValueChange = {},
            label = { Text("Entreprise finale") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Multi-sélecteur de contacts
        ContactSelectorField(
            label = "Contacts (optionnel)",
            contactViewModel = contactVm,
            selectedContacts = selectionnedContacts,
            onContactsChanged = { selectionnedContacts = it },
            userId = userId,
            companyId = finalCompanyId.toString(),
            interviewId = interviewId,
        )
        contacts.filter { it.companyId == finalCompanyId }

        ReusableForm(
            fields = fields,
            initialValues = existing?.let {
                mapOf(
                    "location" to (it.interview.location ?: ""),
                    "style" to (it.interview.style ?: ""),
                    "type" to (it.interview.type ?: ""),
                    "preInterviewNotes" to (it.interview.preInterviewNotes ?: ""),
                    "interviewNotes" to (it.interview.interviewNotes ?: ""),
                    "postInterviewNotes" to (it.interview.postInterviewNotes ?: ""),
                    "returnDate" to (it.interview.returnDate?.toString() ?: ""),
                    "testsNeeded" to (it.interview.testsNeeded.toString()),
                    "testsDeadline" to (it.interview.testsDeadline?.toString() ?: ""),
                    "durationMinutes" to (it.interview.durationMinutes?.toString() ?: "")
                )
            } ?: emptyMap(),
            onSubmit = { form ->
                val id = existing?.interview?.id ?: UUID.randomUUID().toString()
                val hash = existing?.interview?.base?.syncHash ?: "interview-$id"
                val companyChanged = existing?.interview?.companyId != finalCompanyId

                val submitAction = {
                    val oldCompany = companies.find { it.id == existing?.interview?.companyId }
                    val newCompany = companies.find { it.id == finalCompanyId }
                    handleCompanyChange(
                        oldCompany = oldCompany,
                        newCompany = newCompany,
                        entityId = id,
                        companyIdField = { it.interviewsIds ?: emptyList() },
                        copyWithIds = { company, newIds -> company.copy(interviewsIds = newIds) },
                        save = { companyVm.save(it) }
                    )

                    val ent = InterviewEntity(
                        id = id,
                        applicationId = selectionnedApplicationId,
                        companyId = finalCompanyId ?: selectionnedCompanyId,
                        dateTime = dateTime,
                        durationMinutes = form["durationMinutes"]?.toIntOrNull(),
                        location = form["location"],
                        style = form["style"],
                        type = form["type"],
                        preInterviewNotes = form["preInterviewNotes"],
                        interviewNotes = form["interviewNotes"],
                        postInterviewNotes = form["postInterviewNotes"],
                        returnDate = form["returnDate"]?.toLongOrNull(),
                        testsNeeded = form["testsNeeded"].toBoolean(),
                        testsDeadline = form["testsDeadline"]?.toLongOrNull(),
                        typeId = existing?.interview?.typeId,
                        styleId = existing?.interview?.styleId,
                        contactsIds = selectionnedContacts.map { it.id },
                        base = existing?.interview?.base ?: CommonEntityFields(
                            userId = userId,
                            syncHash = hash
                        )
                    )
                    interviewVm.save(ent, selectionnedContacts.map { it.id })
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