package com.delhomme.jobbingtrack.ui.major.entretiens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.classes.Entretien
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.data.forms.FormSuggestions
import com.delhomme.jobbingtrack.ui.components.ContactSelectorField
import com.delhomme.jobbingtrack.ui.components.EntitySelectorField
import com.delhomme.jobbingtrack.ui.components.ModernDateTimePickerField
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import com.delhomme.jobbingtrack.utils.toFieldMap
import com.delhomme.jobbingtrack.utils.toSafeFieldMap


@Composable
fun AddOrEditEntretienScreen(
    navController: NavController? = null,
    existingEntretienData: Entretien? = null,
    linkedCandidatureId: String? = null,
    linkedCompanyId: String? = null,
    onSave: (Map<String, String>) -> Unit,
    onCancel: (() -> Unit)? = null
) {
    var selectedContacts by remember { mutableStateOf<List<Contact>>(emptyList()) }
    var dateTimeMillis by remember { mutableStateOf(existingEntretienData?.dateTime ?: System.currentTimeMillis()) }

    var selectedCandidatureId by remember {
        mutableStateOf(existingEntretienData?.candidatureId ?: linkedCandidatureId)
    }

    val entrepriseIdFromCandidature = selectedCandidatureId?.let {
        FakeDataProvider.candidatures.find { c -> c.id == it }?.companyId
    }

    val effectiveCompanyId = entrepriseIdFromCandidature ?: linkedCompanyId

    val fields = listOf(
        FormField("location", "Lieu de l'entretien", FieldType.TEXT),
        FormField("style", "Style d'entretien", FieldType.DROPDOWN, isRequired = true, options = FormSuggestions.entretienStyles),
        FormField("type", "Type d'entretien", FieldType.DROPDOWN, isRequired = true, options = FormSuggestions.entretienTypes),
        FormField("preInterviewNotes", "Notes avant entretien", FieldType.MULTILINE_TEXT),
        FormField("interviewNotes", "Notes pendant entretien", FieldType.MULTILINE_TEXT),
        FormField("postInterviewNotes", "Notes après entretien", FieldType.MULTILINE_TEXT),
        FormField("returnDate", "Date de retour attendu", FieldType.DATE),
        FormField("testsNeeded", "Tests requis ?", FieldType.BOOLEAN),
        FormField("testsDeadline", "Date limite pour les tests", FieldType.DATE)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ModernDateTimePickerField(
            label = "Date et heure de l'entretien",
            initialMillis = dateTimeMillis,
            onDateTimeSelected = { dateTimeMillis = it }
        )


        if (linkedCandidatureId == null) {
            EntitySelectorField(
                label = "Candidature",
                selectedEntityId = selectedCandidatureId,
                allEntities = FakeDataProvider.candidatures,
                getEntityLabel = { it.title },
                onEntitySelected = {
                    selectedCandidatureId = it.id
                }
            )
        } else {
            OutlinedTextField(
                value = FakeDataProvider.candidatures.find { it.id == linkedCandidatureId }?.title ?: "",
                onValueChange = {},
                label = { Text("Candidature") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        OutlinedTextField(
            value = FakeDataProvider.entreprises.find { it.id == effectiveCompanyId }?.name ?: "",
            onValueChange = {},
            label = { Text("Entreprise") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        ContactSelectorField(
            label = "Ajouter des contacts",
            allContacts = FakeDataProvider.contacts,
            selectedContacts = selectedContacts,
            onContactsChanged = { selectedContacts = it }
        )

        ReusableForm(
            fields = fields,
            initialValues = existingEntretienData?.toFieldMap()?.toSafeFieldMap() ?: emptyMap(),
            onSubmit = { formData ->
                onSave(
                    formData + mapOf(
                        "dateTime" to dateTimeMillis.toString(),
                        "candidatureId" to (selectedCandidatureId ?: ""),
                        "companyId" to (effectiveCompanyId ?: ""),
                        "contacts" to selectedContacts.joinToString(",") { it.id }
                    )
                )
                navController?.popBackStack()
            },
            onCancel = onCancel
        )
    }
}
