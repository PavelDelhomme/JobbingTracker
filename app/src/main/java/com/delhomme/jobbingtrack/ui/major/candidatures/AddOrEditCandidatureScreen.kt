package com.delhomme.jobbingtrack.ui.major.candidatures

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntrepriseViewModel
import com.delhomme.jobbingtrack.ui.components.forms.selectors.EntitySelectorField
import com.delhomme.jobbingtrack.ui.components.forms.ReusableForm
import com.delhomme.jobbingtrack.utils.toFieldMap
import java.util.UUID

@Composable
fun AddOrEditCandidatureScreen(
    candidatureId: String? = null,
    linkedEntrepriseId: String? = null,
    onCancel: () -> Unit,
    candidatureVm: CandidatureViewModel = viewModel(),
    entrepriseVm: EntrepriseViewModel = viewModel()
) {
    // 1) Observer la liste
    val allCands by candidatureVm.candidatures.observeAsState(emptyList())
    val allEnts by entrepriseVm.entreprises.observeAsState(emptyList())

    // 2) Chercher l’existante si on édite
    val existing = allCands.find { it.id == candidatureId }

    // État pour le sélecteur d’entreprise
    var selectedCompanyId by remember {
        mutableStateOf(
            existing?.companyId
                ?: linkedEntrepriseId
                ?: ""
        )
    }

    // 3) Champs du formulaire
    val fields = listOf(
        FormField("title",           "Titre du poste",        FieldType.TEXT,              isRequired = true),
        FormField("applicationDate", "Date de candidature",   FieldType.DATE,              isRequired = true),
        FormField("platform",        "Plateforme",            FieldType.SUGGESTION_TEXT,   options = listOf("LinkedIn","Indeed","HelloWork")),
        FormField("contractType",    "Type de contrat",       FieldType.SUGGESTION_TEXT,   options = listOf("CDI","CDD","Stage")),
        FormField("location",        "Lieu du poste",         FieldType.SUGGESTION_TEXT),
        FormField("applicationType","Type de candidature",   FieldType.DROPDOWN,          options = listOf("ONLINE","SPONTANEOUS"), isRequired = true),
        FormField("applicationStatus","Statut",              FieldType.DROPDOWN,          options = listOf("PENDING","ACCEPTED","REJECTED"), isRequired = true),
        FormField("isArchived",      "Archiver directement ?", FieldType.BOOLEAN),
        FormField("notes",           "Notes",                 FieldType.MULTILINE_TEXT)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Sélecteur d’entreprise
        EntitySelectorField(
            label = "Entreprise",
            selectedEntityId = selectedCompanyId,
            allEntities = allEnts,
            getEntityLabel = { it.name },
            onEntitySelected = { selectedCompanyId = it.id },
            allowCreation = true,
            onCreateEntity = { name ->
                // Créer une nouvelle EntrepriseEntity et la sauvegarder
                val newId = UUID.randomUUID().toString()
                entrepriseVm.save(
                    EntrepriseEntity(
                        id = newId,
                        name = name,
                        type = null,
                        phone = null,
                        email = null,
                        hrEmail = null,
                        address = null,
                        notes = null,
                        syncHash = "ent-$newId",
                        isArchived = false,
                        isDeleted = false
                    )
                )
                selectedCompanyId = newId
            }
        )

        // —— Le formulaire principal ——
        ReusableForm(
            fields = fields,
            initialValues = existing?.toFieldMap() ?: emptyMap(),
            onSubmit = { form ->
                // 1) Génération de l'ID et du syncHash
                val id   = existing?.id ?: UUID.randomUUID().toString()
                val hash = existing?.syncHash ?: "cand-$id"

                // 2) Construction de l'entité Room
                val entity = CandidatureEntity(
                    id              = id,
                    title           = form["title"]!!,
                    companyId       = selectedCompanyId,
                    applicationDate = form["applicationDate"]!!.toLong(),
                    platform        = form["platform"]?.takeIf(String::isNotBlank),
                    contractType    = form["contractType"]?.takeIf(String::isNotBlank),
                    location        = form["location"]?.takeIf(String::isNotBlank),
                    applicationType = form["applicationType"]!!,
                    applicationStatus = form["applicationStatus"]!!,
                    isArchived      = form["isArchived"]!!.toBoolean(),
                    notes           = form["notes"]?.takeIf(String::isNotBlank),
                    syncHash        = hash,
                    isDeleted       = existing?.isDeleted ?: false
                )

                // 3) Sauvegarde via le ViewModel
                candidatureVm.save(entity)

                // 4) Fermeture du sheet ou popBackStack
                onCancel()
            },
            onCancel = onCancel
        )
    }
}