package com.delhomme.jobbingtrack.applications.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.ui.dialogs.ReusableConfirmDialog
import com.delhomme.jobbingtrack.commons.ui.forms.FieldType
import com.delhomme.jobbingtrack.commons.ui.forms.FormField
import com.delhomme.jobbingtrack.commons.ui.forms.ReusableForm
import com.delhomme.jobbingtrack.commons.ui.forms.selectors.EntitySelectorField
import com.delhomme.jobbingtrack.utils.handleCompanyChange
import com.delhomme.jobbingtrack.utils.toFieldMap
import java.util.UUID
import kotlin.collections.find

@Composable
fun AddOrEditApplicationScreen(
    applicationId: String? = null,
    linkedCompanyId: String? = null,
    onCancel: () -> Unit,
    applicationVm: ApplicationViewModel = hiltViewModel(),
    companyVm: CompanyViewModel = hiltViewModel(),
    userId: String
) {
    // 1) Observer la liste
    val allApplications by applicationVm.allForUser(userId = userId).observeAsState(emptyList())
    val allCompanies by companyVm.allForUser(userId = userId).observeAsState(emptyList())

    // 2) Chercher l’existante si on édite
    val existing = allApplications.find { it.id == applicationId }
    val applicationId   = existing?.id ?: UUID.randomUUID().toString()

    // État pour le sélecteur d’entreprise
    var selectedCompanyId by remember { mutableStateOf(existing?.companyId ?: linkedCompanyId ?: "") }

    var showUnlinkDialog by remember { mutableStateOf(false) }
    var pendingSubmit by remember { mutableStateOf<(() -> Unit)?>(null) }


    // Confirmation dialog
    ReusableConfirmDialog(
        show = showUnlinkDialog,
        title = "Changer d'entreprise ?",
        message = "Cette candidature sera déliée de l'ancienne entreprise et rattachée à la nouvelle. Voulez-vous continuer ?",
        onConfirm = {
            showUnlinkDialog = false
            pendingSubmit?.invoke()
        },
        onDismiss = { showUnlinkDialog = false }
    )


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
            allEntities = allCompanies,
            getEntityLabel = { it.name },
            onEntitySelected = { selectedCompanyId = it.id },
            allowCreation = true,
            onCreateEntity = { name ->
                // Créer une nouvelle CompanyEntity et la sauvegarder
                val newId = UUID.randomUUID().toString()
                companyVm.save(
                    CompanyEntity(
                        id = newId,
                        name = name,
                        type = null,
                        phone = null,
                        email = null,
                        hrEmail = null,
                        address = null,
                        notes = null,
                        base = CommonEntityFields(
                            userId = userId,
                            syncHash = "cmp-$newId",
                        )
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
                val hash = existing?.base?.syncHash ?: "application-$applicationId"
                val companyChanged = existing?.companyId != selectedCompanyId

                val submitAction = {
                    val oldCompany = allCompanies.find { it.id == existing?.companyId }
                    val newCompany = allCompanies.find { it.id == selectedCompanyId }

                    val entity = ApplicationEntity(
                        id = applicationId,
                        title = form["title"]!!,
                        companyId = selectedCompanyId,
                        applicationDate = form["applicationDate"]!!.toLong(),
                        platformId = form["platformId"]?.takeIf(String::isNotBlank),
                        contractTypeId = form["contractTypeId"]?.takeIf(String::isNotBlank),
                        location = form["location"]?.takeIf(String::isNotBlank),
                        applicationTypeId = form["applicationTypeId"]!!,
                        applicationStatusId = form["applicationStatusId"]!!,
                        notes = form["notes"]?.takeIf(String::isNotBlank),
                        base = CommonEntityFields(
                            userId = userId,
                            createdAt = existing?.base?.createdAt ?: System.currentTimeMillis(),
                            updatedAt = System.currentTimeMillis(),
                            deletedAt = existing?.base?.deletedAt,
                            isDeleted = existing?.base?.isDeleted ?: false,
                            isArchived = form["isArchived"]!!.toBoolean(),
                            archivedAt = null,
                            syncHash = hash
                        )
                    )
                    applicationVm.save(entity)
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