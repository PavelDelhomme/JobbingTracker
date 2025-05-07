package com.delhomme.jobbingtrack.ui.candidatures

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.remember
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Candidature
import com.delhomme.jobbingtrack.data.forms.FormSuggestions
import com.delhomme.jobbingtrack.utils.toFieldMap


@Composable
fun AddOrEditCandidatureScreen(
    navController: NavController? = null,
    existingCandidatureData: Candidature? = null, // null pour ajout
    onSave: (Map<String, String>) -> Unit,
    onCancel: (() -> Unit)? = null,
) {

    // todo pré-remplissage des champs avec existingCandidatureData
    val entrepriseSuggestions = FakeDataProvider.entreprises.map { it.name }.distinct()
    val knownPlatforms = listOf("LinkedIn", "Indeed", "Welcome to the Jungle", "HelloWork", "Pôle Emploi")
    val typePostes = listOf("Développeur", "Designer", "Chef de Projet", "Data Analyst", "DevOps")


    val fields = listOf(
        FormField(name = "title", label = "Titre du poste", type = FieldType.TEXT, isRequired = true),
        FormField(name = "companyName", label = "Entreprise", type = FieldType.SUGGESTION_TEXT, options = entrepriseSuggestions, isRequired = true),
        FormField(name = "applicationDate", label = "Date de candidature", type = FieldType.DATE, isRequired = true),

        FormField(
            name = "platform",
            label = "Plateforme",
            type = FieldType.SUGGESTION_TEXT,
            options = FormSuggestions.platforms,
            onNewOptionAdded = { FormSuggestions.platforms.add(it) },
            onOptionRemoved = { FormSuggestions.platforms.remove(it) },
            onOptionRenamed = { old, new ->
                val index = FormSuggestions.platforms.indexOf(old)
                if (index != -1) FormSuggestions.platforms[index] = new
            }
        ),

        FormField(
            name = "contractType",
            label = "Type de contrat",
            type = FieldType.SUGGESTION_TEXT,
            options = FormSuggestions.contractTypes,
            onNewOptionAdded = { FormSuggestions.contractTypes.add(it) },
            onOptionRemoved = { FormSuggestions.contractTypes.remove(it) },
            onOptionRenamed = { old, new ->
                val index = FormSuggestions.contractTypes.indexOf(old)
                if (index != -1) FormSuggestions.contractTypes[index] = new
            }
        ),

        FormField(name = "location", label = "Lieu du poste", type = FieldType.TEXT),
        FormField(name = "isSpontaneous", label = "Candidature Spontanée", type = FieldType.BOOLEAN),
        FormField(name = "notes", label = "Notes", type = FieldType.MULTILINE_TEXT)
    )


    ReusableForm(
        fields = fields,
        initialValues = existingCandidatureData?.toFieldMap(),
        onSubmit = { formData ->
            onSave(formData)
            navController?.popBackStack() // Retour automatique après sauvegarde
        },
        onCancel = onCancel
    )
}