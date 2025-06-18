package com.delhomme.jobbingtrack.contacts.ui



import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.ui.dialogs.ReusableConfirmDialog
import com.delhomme.jobbingtrack.commons.ui.forms.FieldType
import com.delhomme.jobbingtrack.commons.ui.forms.FormField
import com.delhomme.jobbingtrack.commons.ui.forms.ReusableForm
import com.delhomme.jobbingtrack.commons.ui.forms.selectors.EntitySelectorField
import com.delhomme.jobbingtrack.datas.entities.companies.CompanyEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.DepartmentTypeEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.PositionTypeEntity
import com.delhomme.jobbingtrack.datas.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.CallViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.CompanyViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.ContactViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.DepartmentTypeViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.FollowUpViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.PositionTypeViewModel
import java.util.UUID


@Composable
fun AddOrEditContactScreen(
    contactId: String? = null,
    linkedApplicationId: String? = null,
    linkedCompanyId: String? = null,
    linkedFollowUpId: String? = null,
    linkedCallId: String? = null,
    linkedInterviewId: String? = null,
    userId: String,
    onCancel: () -> Unit,
    contactVm: ContactViewModel = hiltViewModel(),
    companyVm: CompanyViewModel = hiltViewModel(),
    applicationVm: ApplicationViewModel = hiltViewModel(),
    followUpVm: FollowUpViewModel = hiltViewModel(),
    callVm: CallViewModel = hiltViewModel(),
    departmentTypeVm: DepartmentTypeViewModel = hiltViewModel(),
    positionTypeVm: PositionTypeViewModel = hiltViewModel(),
) {
    // 1) Charger les listes
    val allContacts by contactVm.allForUser(userId).observeAsState(emptyList())
    val allCompanies by companyVm.allForUser(userId).observeAsState(emptyList())
    val allApplications by applicationVm.allForUser(userId).observeAsState(emptyList())
    val allFollowUps by followUpVm.allForUser(userId).observeAsState(emptyList())
    val allCalls by callVm.allForUser(userId).observeAsState(emptyList())
    val allDepartments by departmentTypeVm.all.observeAsState(emptyList())
    val allPositions by positionTypeVm.all.observeAsState(emptyList())

    // 2) Chercher l’existant si on édite
    val existing = contactId?.let { id -> allContacts.find { it.id == id } }

    // 3) État local pour l’entreprise liée
    var selCompanyId by remember { mutableStateOf(existing?.companyId ?: linkedCompanyId.orEmpty()) }
    var selectedDepartmentId by remember { mutableStateOf(existing?.departmentId ?: "") }
    var selectedPositionId by remember { mutableStateOf(existing?.positionId ?: "") }

    var showUnlinkDialog by remember { mutableStateOf(false) }
    var pendingSubmit by remember { mutableStateOf<(() -> Unit)?>(null) }

    // Résolution de la company finale (optionnel, selon ta logique)
    val finalCompanyId = selCompanyId

    // 4) Les champs du formulaire
    val fields = listOf(
        FormField("firstName", "Prénom", FieldType.TEXT, isRequired = true),
        FormField("lastName", "Nom", FieldType.TEXT, isRequired = true),
        FormField("phone", "Téléphone", FieldType.PHONE),
        FormField("email", "Email", FieldType.EMAIL),
        FormField("notes", "Notes", FieldType.MULTILINE_TEXT)
    )

    // --- DIALOG MODERNE ET REUTILISABLE ---
    ReusableConfirmDialog(
        show = showUnlinkDialog,
        title = "Changer d'entreprise ?",
        message = "Ce contact sera délié de l'ancienne entreprise et rattaché à la nouvelle. Voulez-vous continuer ?",
        onConfirm = {
            showUnlinkDialog = false
            pendingSubmit?.invoke()
        },
        onDismiss = { showUnlinkDialog = false }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Sélecteur d’entreprise
        EntitySelectorField(
            label = "Entreprise liée",
            selectedEntityId = finalCompanyId,
            allEntities = allCompanies,
            getEntityLabel = { it.name },
            onEntitySelected = { selCompanyId = it.id },
            allowCreation = true,
            onCreateEntity = { name ->
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
                            syncHash = "ent-$newId"
                        )
                    )
                )
                selCompanyId = newId
            }
        )

        // Département
        EntitySelectorField(
            label = "Département",
            selectedEntityId = selectedDepartmentId,
            allEntities = allDepartments,
            getEntityLabel = { it.name },
            onEntitySelected = { selectedDepartmentId = it.id },
            allowCreation = true,
            onCreateEntity = { name ->
                val newId = UUID.randomUUID().toString()
                departmentTypeVm.save(
                    DepartmentTypeEntity(
                        id = newId,
                        name = name,
                        companyId = finalCompanyId
                    )
                )
                selectedDepartmentId = newId
            }
        )

        // Poste
        EntitySelectorField(
            label = "Poste",
            selectedEntityId = selectedPositionId,
            allEntities = allPositions,
            getEntityLabel = { it.label },
            onEntitySelected = { selectedPositionId = it.id },
            allowCreation = true,
            onCreateEntity = { label ->
                val newId = UUID.randomUUID().toString()
                positionTypeVm.save(
                    PositionTypeEntity(
                        id = newId,
                        label = label,
                        base = CommonEntityFields(userId = userId, syncHash = "pos-$newId")
                    )
                )
                selectedPositionId = newId
            }
        )

        // Formulaire principal
        ReusableForm(
            fields = fields,
            initialValues = existing?.run {
                mapOf(
                    "firstName" to (firstName ?: ""),
                    "lastName" to (lastName ?: ""),
                    "phone" to (phone ?: ""),
                    "email" to (email ?: ""),
                    "notes" to (notes ?: "")
                )
            } ?: emptyMap(),
            onSubmit = { form ->
                val id = existing?.id ?: UUID.randomUUID().toString()
                val hash = existing?.base?.syncHash ?: "ct-$id"
                val companyChanged = existing?.companyId != finalCompanyId

                val submitAction = {
                    contactVm.save(
                        ContactEntity(
                            id = id,
                            firstName = form["firstName"] ?: "",
                            lastName = form["lastName"] ?: "",
                            phone = form["phone"],
                            email = form["email"],
                            positionId = selectedPositionId.ifBlank { null },
                            departmentId = selectedDepartmentId.ifBlank { null },
                            companyId = finalCompanyId,
                            notes = form["notes"],
                            base = existing?.base ?: CommonEntityFields(userId = userId, syncHash = hash)
                        )
                    )
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