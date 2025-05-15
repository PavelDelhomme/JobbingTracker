package com.delhomme.jobbingtrack.ui.major.appels

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
import com.delhomme.jobbingtrack.data.classes.Appel
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.ui.components.EntitySelectorField
import com.delhomme.jobbingtrack.utils.toFieldMap
import com.delhomme.jobbingtrack.utils.toSafeFieldMap

@Composable
fun AddOrEditAppelScreen(
    navController: NavController? = null,
    existingAppelData: Appel? = null,
    linkedCandidatureId: String? = null,
    linkedCompanyId: String? = null,
    onSave: (Map<String, String>) -> Unit,
    onCancel: (() -> Unit)? = null,
) {
    var selectedCandidatureId by remember { mutableStateOf(existingAppelData?.candidatureId ?: linkedCandidatureId) }
    var selectedContactId by remember { mutableStateOf(existingAppelData?.contactId ?: "") }

    // Calcul intelligent de l'entreprise
    val entrepriseIdFromCandidature = selectedCandidatureId?.let {
        FakeDataProvider.candidatures.find { c -> c.id == it }?.companyId
    }

    val effectiveCompanyId = entrepriseIdFromCandidature ?: linkedCompanyId

    val fields = listOf(
        FormField("dateTime", "Date et heure de l'appel", FieldType.DATE, isRequired = true),
        FormField(
            "subject",
            "Objet de l'appel",
            FieldType.SUGGESTION_TEXT,
            options = listOf("Appel de suivi", "Prise de contact", "Demande d'informations"),
            isRequired = true
        ),
        FormField("relanceId", "Relance liée (optionnel)", FieldType.SELECTION),
        FormField("notes", "Notes sur l'appel", FieldType.MULTILINE_TEXT)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (linkedCandidatureId == null) {
            EntitySelectorField(
                label = "Candidature (optionnel)",
                selectedEntityId = selectedCandidatureId,
                allEntities = FakeDataProvider.candidatures,
                getEntityLabel = { it.title },
                onEntitySelected = { selectedCandidatureId = it.id }
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

        // Toujours obligatoire visuellement (non modifiable si déduit), mais jamais éditable manuellement
        OutlinedTextField(
            value = FakeDataProvider.entreprises.find { it.id == effectiveCompanyId }?.name ?: "Aucune entreprise",
            onValueChange = {},
            label = { Text("Entreprise") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Contact optionnel avec création rapide
        EntitySelectorField(
            label = "Contact (optionnel)",
            selectedEntityId = selectedContactId,
            allEntities = FakeDataProvider.contacts.filter { it.entrepriseId == effectiveCompanyId },
            getEntityLabel = { "${it.firstName} ${it.lastName}" },
            onEntitySelected = { selectedContactId = it.id },
            allowCreation = true,
            onCreateEntity = { fullName ->
                val parts = fullName.split(" ", limit = 2)
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
            initialValues = existingAppelData?.toFieldMap()?.toMutableMap()?.apply {
                existingAppelData.dateTime.let { this["dateTime"] = it.toString() }
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
