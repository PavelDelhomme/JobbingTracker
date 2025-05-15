package com.delhomme.jobbingtrack.ui.major.relances

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Relance
import com.delhomme.jobbingtrack.data.classes.toSafeFieldMap
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.data.forms.FormSuggestions
import com.delhomme.jobbingtrack.ui.components.EntitySelectorField
import com.delhomme.jobbingtrack.utils.toFieldMap

@Composable
fun AddOrEditRelanceScreen(
    navController: NavController? = null,
    existingRelanceData: Relance? = null,
    linkedCandidatureId: String? = null,
    linkedCompanyId: String? = null,
    onSave: (Map<String, String>) -> Unit,
    onCancel: (() -> Unit)? = null,
) {
    var selectedCandidatureId by remember {
        mutableStateOf(existingRelanceData?.candidatureId ?: linkedCandidatureId)
    }
    var selectedContactId by remember { mutableStateOf(existingRelanceData?.contactId ?: "") }

    // Ondéduit l'entreprie à partir de la candidature sélectionnée, sinon on prend celle passée manueklllement
    val entrepriseIdFromCandidature = selectedCandidatureId?.let {
        FakeDataProvider.candidatures.find { c -> c.id == it }?.companyId
    }

    val effectiveCompanyId = entrepriseIdFromCandidature ?: linkedCompanyId

    val fields = listOf(
        FormField("date", "Date de relance", FieldType.DATE, isRequired = true),
        FormField(
            "type",
            "Type de relance",
            FieldType.DROPDOWN,
            isRequired = true,
            options = FormSuggestions.relanceTypes,
            onNewOptionAdded = { FormSuggestions.relanceTypes.add(it) },
            onOptionRemoved = { FormSuggestions.relanceTypes.remove(it) },
            onOptionRenamed = { old, new ->
                val index = FormSuggestions.relanceTypes.indexOf(old)
                if (index != -1) FormSuggestions.relanceTypes[index] = new
            }
        ),
        FormField("responseStatus", "Statut réponse (En attente, Positif, Négatif, Aucun retour)", FieldType.TEXT),
        FormField("notes", "Notes", FieldType.MULTILINE_TEXT)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (linkedCandidatureId == null) {
            EntitySelectorField(
                label = "Candidature",
                selectedEntityId = selectedCandidatureId,
                allEntities = FakeDataProvider.candidatures,
                getEntityLabel = { it.title },
                onEntitySelected = { selectedCandidatureId == it.id }
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

        // ✅ Sélecteur intelligent de contact (optionnel)
        EntitySelectorField(
            label = "Contact (optionnel)",
            selectedEntityId = selectedContactId,
            allEntities = FakeDataProvider.contacts.filter { it.entrepriseId == effectiveCompanyId },
            getEntityLabel = { "${it.firstName} ${it.lastName}" },
            onEntitySelected = { selectedContactId = it.id },
            allowCreation = true,
            onCreateEntity = { fullName ->
                val parts = fullName.trim().split(" ", limit = 2)
                val newContact = FakeDataProvider.addContactIfNotExists(
                    firstName = parts.getOrElse(0) { "" },
                    lastName = parts.getOrElse(1) { "" },
                    entrepriseId = effectiveCompanyId ?: ""
                )
                selectedContactId = newContact.id
            }
        )

        ReusableForm(
            fields = fields,
            initialValues = existingRelanceData?.toFieldMap()?.toMutableMap()?.apply {
                existingRelanceData.date.let { this["date"] = it.toString() }
            } ?: emptyMap(),
            onSubmit = { formData ->
                onSave(
                    formData + mapOf(
                        "candidatureId" to (selectedCandidatureId ?: ""),
                        "companyId" to (effectiveCompanyId ?: ""),
                        "contactId" to (selectedContactId)
                    )
                )
                navController?.popBackStack()
            },
            onCancel = onCancel
        )

    }
}