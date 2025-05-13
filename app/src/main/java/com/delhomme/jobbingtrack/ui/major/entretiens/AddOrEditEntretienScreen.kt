package com.delhomme.jobbingtrack.ui.major.entretiens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ModernDateTimePickerField(
            label = "Date et heure de l'entretien",
            initialMillis = dateTimeMillis,
            onDateTimeSelected = { dateTimeMillis = it }
        )

        EntitySelectorField(
            label = "Candidature",
            selectedEntityId = linkedCandidatureId,
            allEntities = FakeDataProvider.candidatures,
            getEntityLabel = { it.title },
            onEntitySelected = { /* handled later in onSubmit */ }
        )

        EntitySelectorField(
            label = "Entreprise",
            selectedEntityId = linkedCompanyId,
            allEntities = FakeDataProvider.entreprises,
            getEntityLabel = { it.name },
            onEntitySelected = { /* handled later in onSubmit */ }
        )

        ContactSelectorField(
            label = "Ajouter des contacts",
            allContacts = FakeDataProvider.contacts,
            selectedContacts = selectedContacts,
            onContactsChanged = { selectedContacts = it }
        )

        ReusableForm(
            fields = fields,
            initialValues = existingEntretienData?.toFieldMap(),
            onSubmit = { formData ->
                onSave(
                    formData + mapOf(
                        "dateTime" to dateTimeMillis.toString(),
                        "candidatureId" to (linkedCandidatureId ?: ""),
                        "companyId" to (linkedCompanyId ?: ""),
                        "contacts" to selectedContacts.joinToString(",") { it.id }
                    )
                )
                navController?.popBackStack()
            },
            onCancel = onCancel
        )
    }
}
