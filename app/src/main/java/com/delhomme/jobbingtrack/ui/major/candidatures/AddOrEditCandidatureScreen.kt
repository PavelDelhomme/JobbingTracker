package com.delhomme.jobbingtrack.ui.major.candidatures

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.ApplicationStatus
import com.delhomme.jobbingtrack.data.classes.ApplicationType
import com.delhomme.jobbingtrack.data.classes.Candidature
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.data.forms.FormSuggestions
import com.delhomme.jobbingtrack.ui.components.EntitySelectorField
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import com.delhomme.jobbingtrack.utils.toFieldMap

@Composable
fun AddOrEditCandidatureScreen(
    navController: NavController? = null,
    existingCandidatureData: Candidature? = null,
    onSave: (Map<String, String>) -> Unit,
    onCancel: (() -> Unit)? = null,
) {
    val entrepriseSuggestions = FakeDataProvider.entreprises.map { it.name }.distinct()
    val knownLocations = FakeDataProvider.entreprises.mapNotNull { it.address }.distinct()

    val fields = listOf(
        FormField("title", "Titre du poste", FieldType.TEXT, isRequired = true),
        FormField("applicationDate", "Date de candidature", FieldType.DATE, isRequired = true),
        FormField("platform", "Plateforme", FieldType.SUGGESTION_TEXT, options = FormSuggestions.platforms, onNewOptionAdded = { FormSuggestions.platforms.add(it) }),
        FormField("contractType", "Type de contrat", FieldType.SUGGESTION_TEXT, options = FormSuggestions.contractTypes, onNewOptionAdded = { FormSuggestions.contractTypes.add(it) }),
        FormField("location", "Lieu du poste", FieldType.SUGGESTION_TEXT, options = knownLocations),
        FormField("applicationType", "Type de candidature", FieldType.DROPDOWN, options = ApplicationType.values().map { it.name }, isRequired = true),
        FormField("applicationStatus", "Statut de la candidature", FieldType.DROPDOWN, options = ApplicationStatus.values().map { it.name }, isRequired = true),
        FormField("isArchived", "Archiver la candidature directement ?", FieldType.BOOLEAN),
        FormField("notes", "Notes", FieldType.MULTILINE_TEXT)
    )

    var selectedEntrepriseId by remember { mutableStateOf(existingCandidatureData?.companyId ?: "") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                onSave(formData + mapOf("companyId" to selectedEntrepriseId))
                navController?.popBackStack()
            },
            onCancel = onCancel
        )
    }
}
