package com.delhomme.jobbingtrack.ui.major.candidatures

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.ApplicationStatus
import com.delhomme.jobbingtrack.data.classes.ApplicationType
import com.delhomme.jobbingtrack.data.classes.Candidature
import com.delhomme.jobbingtrack.data.forms.FormSuggestions
import com.delhomme.jobbingtrack.ui.components.EntitySelectorField
import com.delhomme.jobbingtrack.utils.createEntreprise
import com.delhomme.jobbingtrack.utils.extractIdFromFormattedString
import com.delhomme.jobbingtrack.utils.toFieldMap


@Composable
fun AddOrEditCandidatureScreen(
    navController: NavController? = null,
    existingCandidatureData: Candidature? = null, // null pour ajout
    onSave: (Map<String, String>) -> Unit,
    onCancel: (() -> Unit)? = null,
) {
    val entrepriseSuggestions = FakeDataProvider.entreprises.map { it.name }.distinct()
    val knownLocations = FakeDataProvider.entreprises.mapNotNull { it.address }.distinct()

    val fields = listOf(
        FormField(name = "title", label = "Titre du poste", type = FieldType.TEXT, isRequired = true),
        FormField(
            name = "applicationDate",
            label = "Date de candidature",
            type = FieldType.DATE,
            isRequired = true
        ),
        FormField(
            name = "platform",
            label = "Plateforme",
            type = FieldType.SUGGESTION_TEXT,
            options = FormSuggestions.platforms,
            onNewOptionAdded = { FormSuggestions.platforms.add(it) }
        ),
        FormField(
            name = "contractType",
            label = "Type de contrat",
            type = FieldType.SUGGESTION_TEXT,
            options = FormSuggestions.contractTypes,
            onNewOptionAdded = { FormSuggestions.contractTypes.add(it) }
        ),
        FormField(
            name = "location",
            label = "Lieu du poste",
            type = FieldType.SUGGESTION_TEXT,
            options = knownLocations
        ),
        FormField(
            name = "applicationType",
            label = "Type de candidature",
            type = FieldType.DROPDOWN,
            options = ApplicationType.values().map { it.name },
            isRequired = true
        ),
        FormField(
            name = "applicationStatus",
            label = "Statut de la candidature",
            type = FieldType.DROPDOWN,
            options = ApplicationStatus.values().map { it.name },
            isRequired = true
        ),
        FormField(
            name = "isArchived",
            label = "Archiver la candidature directement ?",
            type = FieldType.BOOLEAN
        ),
        FormField(name = "notes", label = "Notes", type = FieldType.MULTILINE_TEXT)
    )

    var selectedEntrepriseId by remember { mutableStateOf(existingCandidatureData?.companyId ?: "") }


    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        EntitySelectorField(
            label = "Entreprise",
            selectedEntityId = selectedEntrepriseId,
            allEntities = FakeDataProvider.entreprises,
            getEntityLabel = { it.name },
            onEntitySelected = { selectedEntrepriseId = it.id },
            allowCreation = true,
            onCreateEntity = { newName ->
                val newEnt = FakeDataProvider.addEntrepriseIfNotExists(newName)
                selectedEntrepriseId = newEnt.id
            }
        )

        ReusableForm(
            fields = fields,
            initialValues = existingCandidatureData?.toFieldMap(),
            onSubmit = { formData ->
                onSave(
                    formData + mapOf("companyId" to selectedEntrepriseId)
                )
                navController?.popBackStack()
            },
            onCancel = onCancel
        )
    }
}