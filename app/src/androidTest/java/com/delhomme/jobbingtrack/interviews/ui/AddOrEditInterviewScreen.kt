package com.delhomme.jobbingtrack.interviews.ui


import android.annotation.SuppressLint

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.delhomme.jobbingtrack.data.forms.*
import com.delhomme.jobbingtrack.data.local.entities.*

import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.data.local.entities.contact.ContactEntity
import com.delhomme.jobbingtrack.data.local.entities.interview.InterviewEntity

import com.delhomme.jobbingtrack.data.viewmodel.DashboardViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Interview.InterviewViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Application.ApplicationViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Company.CompanyViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Contact.ContactViewModel

import com.delhomme.jobbingtrack.ui.components.forms.ReusableForm
import com.delhomme.jobbingtrack.ui.components.forms.selectors.ContactSelectorField
import com.delhomme.jobbingtrack.ui.components.forms.selectors.EntitySelectorField
import com.delhomme.jobbingtrack.ui.components.forms.ModernDateTimePickerField

import com.delhomme.jobbingtrack.utils.*

import java.util.UUID

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddOrEditInterviewScreen(
    viewModel: DashboardViewModel = viewModel(),
    interviewId: String?,
    linkedApplicationId: String? = null,
    linkedCompanyId: String? = null,
    onCancel: () -> Unit,
    interviewVm: InterviewViewModel = viewModel(),
    applicationVm: ApplicationViewModel = viewModel(),
    companyVm: CompanyViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    userId: String
) {

    // 1) on collecte les listes brutes
    val interviews    by viewModel.interviewsFlow.collectAsState(initial = emptyList())
    val applications  by viewModel.applicationsFlow.collectAsState(initial = emptyList())
    val companies     by viewModel.companiesFlow.collectAsState(initial = emptyList())
    val contacts      by viewModel.contactsFlow.collectAsState(initial = emptyList())


    // 2) on récupère éventuellement l'entretien à éditer
    val interview = interviewId
        ?.let { id -> interviews.firstOrNull { it.interview.id == id } }


    // 1) Charger l’entretien + ses contacts
    val liveData = interview?.let { interviewVm.interviewById(interviewId ?: "", it.interview.userId) }
    val liveDataState = liveData?.observeAsState(initial = null)
    val interviewWithContacts = liveDataState?.value
    val existingInterview = interviewWithContacts?.interview
    val existingSelectedCompanyId = existingInterview?.companyId ?: linkedCompanyId ?: ""
    val existingSelectedApplicationId = existingInterview?.applicationId ?: linkedApplicationId ?: ""
    val selInitialContacts: List<ContactEntity> = interviewWithContacts?.contacts ?: emptyList()
    val selDateTime = existingInterview?.dateTime ?: System.currentTimeMillis()

    // 2) États locaux
    var selectionnedApplicationId      by remember { mutableStateOf(existingSelectedApplicationId) }
    var selectionnedCompanyId      by remember { mutableStateOf(existingSelectedCompanyId) }
    var selectionnedContacts    by remember { mutableStateOf(selInitialContacts) }
    var dateTime       by remember { mutableLongStateOf(selDateTime) }

    val finalCompanyId = resolveCompanyId(
        existingInterview = existingInterview,
        applications = applications,
        followUps = emptyList(), // pas utilisé ici
        linkedApplicationId = selectionnedApplicationId,
        fallbackCompanyId = selectionnedCompanyId
    )


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
            userId = userId
        )
        contacts.filter { it.companyId == finalCompanyId }

        ReusableForm(
            fields = fields,
            initialValues = existingInterview?.toFieldMap() ?: emptyMap(),
            onSubmit = { form ->
                val id   = existingInterview?.id ?: UUID.randomUUID().toString()
                val hash = existingInterview?.syncHash ?: "ent-$id"

                val ent = InterviewEntity(
                    id = id,
                    userId = existingInterview?.userId ?: "",
                    applicationId = selectionnedApplicationId,
                    companyId = finalCompanyId ?: selectionnedCompanyId,
                    dateTime = dateTime,
                    durationMinutes = form["durationMinutes"]?.toIntOrNull()
                        ?: existingInterview?.durationMinutes,
                    location = form["location"],
                    style = form["style"],
                    type = form["type"],
                    preInterviewNotes = form["preInterviewNotes"],
                    interviewNotes = form["interviewNotes"],
                    postInterviewNotes = form["postInterviewNotes"],
                    returnDate = form["returnDate"]?.toLongOrNull(),
                    testsNeeded = form["testsNeeded"].toBoolean(),
                    testsDeadline = form["testsDeadline"]?.toLongOrNull(),
                    syncHash = hash,
                    isArchived = existingInterview?.isArchived == true,
                    isDeleted = existingInterview?.isDeleted == true
                )
                interviewVm.save(ent, selectionnedContacts.map { c -> c.id })
                onCancel()
            },
            onCancel = onCancel
        )
    }
}