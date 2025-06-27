package com.delhomme.jobbingtrack.features.interview.presentation.ui

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
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.utils.handleCompanyChange
import com.delhomme.jobbingtrack.core.utils.resolveCompanyId
import com.delhomme.jobbingtrack.features.application.presentation.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.features.company.presentation.viewmodel.CompanyViewModel
import com.delhomme.jobbingtrack.features.contact.presentation.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity
import com.delhomme.jobbingtrack.features.interview.presentation.viewmodel.InterviewStyleViewModel
import com.delhomme.jobbingtrack.features.interview.presentation.viewmodel.InterviewTypeViewModel
import com.delhomme.jobbingtrack.features.interview.presentation.viewmodel.InterviewViewModel
import com.delhomme.jobbingtrack.ui.components.ContactSelectorField
import com.delhomme.jobbingtrack.ui.components.ReusableConfirmDialog
import com.delhomme.jobbingtrack.ui.shared.EntitySelectorField
import com.delhomme.jobbingtrack.ui.shared.FieldType
import com.delhomme.jobbingtrack.ui.shared.FormField
import com.delhomme.jobbingtrack.ui.shared.ModernDateTimePickerField
import com.delhomme.jobbingtrack.ui.shared.ReusableForm
import java.util.UUID
import kotlin.collections.map
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

    val selInitialContacts = if (interviewId == null) {
        emptyList()
    } else {
        interviews.find { it.interview.id == interviewId }?.contacts ?: emptyList()
    }

    // Récupérer l'entretien existant (avec contacts liés)
    val existing = interviews.find { it.interview.id == interviewId }
    val initialContacts = existing?.contacts ?: emptyList()


    var selectedApplicationId by remember { mutableStateOf(existing?.interview?.applicationId ?: linkedApplicationId ?: "") }
    var selectedCompanyId by remember { mutableStateOf(existing?.interview?.companyId ?: linkedCompanyId ?: "") }
    var selectedContacts by remember { mutableStateOf(initialContacts) }
    var selectedTypeId by remember { mutableStateOf(existing?.interview?.typeId) }
    var selectedStyleId by remember { mutableStateOf(existing?.interview?.styleId) }
    var dateTime by remember { mutableStateOf(existing?.interview?.dateTime ?: System.currentTimeMillis()) }

    val finalCompanyId = resolveCompanyId(
        existingInterview = existing?.interview,
        applications = applications,
        followUps = emptyList(),
        linkedApplicationId = selectedApplicationId,
        fallbackCompanyId = selectedCompanyId
    )


    var showUnlinkDialog by remember { mutableStateOf(false) }
    var pendingSubmit by remember { mutableStateOf<(() -> Unit)?>(null) }

    // 5) Vos champs de formulaire
    /*
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
     */


    val fields = listOf(
        FormField("location", "Lieu de l'entretien", FieldType.TEXT),
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
            selectedEntityId = selectedApplicationId,
            allEntities = applications,
            getEntityLabel = { it.title },
            onEntitySelected = { selectedApplicationId = it.id },
            allowCreation = false
        )

        // Si pas de candidature, choisir directement l'entreprise
        if (selectedApplicationId.isBlank()) {
            EntitySelectorField(
                label = "Entreprise",
                selectedEntityId = selectedCompanyId,
                allEntities = companies,
                getEntityLabel = { it.name },
                onEntitySelected = { selectedCompanyId = it.id },
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
            selectedContacts = selectedContacts,
            onContactsChanged = { selectedContacts = it },
            userId = userId,
            companyId = finalCompanyId ?: "",
        )

        contacts.filter { it.companyId == finalCompanyId }

        // Sélecteur pour le type d'entretien
        EntitySelectorField(
            label = "Type d'entretien",
            selectedEntityId = selectedTypeId,
            allEntities = allTypes,
            getEntityLabel = { it.label },
            onEntitySelected = { selectedTypeId = it.id }
        )

        // Sélecteur pour le style d'entretien
        EntitySelectorField(
            label = "Style d'entretien",
            selectedEntityId = selectedStyleId,
            allEntities = allStyles,
            getEntityLabel = { it.label },
            onEntitySelected = { selectedStyleId = it.id }
        )


        ReusableForm(
            fields = fields,
            initialValues = existing?.let {
                mapOf(
                    "location" to (it.interview.location ?: ""),
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
                        companyIdField = { emptyList() }, // ou { null }
                        copyWithIds = { company, _ -> company }, // on ne modifie rien
                        save = { companyVm.save(it) }
                    )

                    val ent = InterviewEntity(
                        id = id,
                        applicationId = selectedApplicationId,
                        companyId = finalCompanyId ?: selectedCompanyId,
                        dateTime = dateTime,
                        durationMinutes = form["durationMinutes"]?.toIntOrNull(),
                        location = form["location"],
                        styleId = selectedStyleId,
                        typeId = selectedTypeId,
                        statusId = existing?.interview?.statusId,
                        preInterviewNotes = form["preInterviewNotes"],
                        interviewNotes = form["interviewNotes"],
                        postInterviewNotes = form["postInterviewNotes"],
                        returnDate = form["returnDate"]?.toLongOrNull(),
                        testsNeeded = form["testsNeeded"].toBoolean(),
                        testsDeadline = form["testsDeadline"]?.toLongOrNull(),
                        base = existing?.interview?.base ?: CommonEntityFields(
                            userId = userId,
                            syncHash = hash
                        )
                    )
                    interviewVm.save(ent, selectedContacts.map { it.id })
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