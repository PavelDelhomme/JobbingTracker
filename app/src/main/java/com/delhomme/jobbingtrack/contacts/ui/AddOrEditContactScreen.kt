package com.delhomme.jobbingtrack.contacts.ui


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.delhomme.jobbingtrack.applications.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.calls.viewmodels.CallViewModel
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.ui.dialogs.ReusableConfirmDialog
import com.delhomme.jobbingtrack.commons.ui.forms.AutoCompleteSingleField
import com.delhomme.jobbingtrack.commons.ui.forms.FieldType
import com.delhomme.jobbingtrack.commons.ui.forms.FormField
import com.delhomme.jobbingtrack.commons.ui.forms.ReusableForm
import com.delhomme.jobbingtrack.commons.ui.forms.selectors.EntitySelectorField
import com.delhomme.jobbingtrack.companies.entities.CompanyEntity
import com.delhomme.jobbingtrack.companies.viewmodels.CompanyViewModel
import com.delhomme.jobbingtrack.contacts.entities.ContactEntity
import com.delhomme.jobbingtrack.contacts.entities.DepartmentTypeEntity
import com.delhomme.jobbingtrack.contacts.entities.PositionTypeEntity
import com.delhomme.jobbingtrack.contacts.viewmodels.ContactViewModel
import com.delhomme.jobbingtrack.contacts.viewmodels.DepartmentTypeViewModel
import com.delhomme.jobbingtrack.contacts.viewmodels.PositionTypeViewModel
import com.delhomme.jobbingtrack.followsup.viewmodels.FollowUpViewModel
import com.delhomme.jobbingtrack.utils.handleCompanyChange
import com.delhomme.jobbingtrack.utils.resolveCompanyId
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
    contactVm: ContactViewModel                 = hiltViewModel(),
    companyVm: CompanyViewModel              = hiltViewModel(),
    applicationVm: ApplicationViewModel         = hiltViewModel(),
    followUpVm: FollowUpViewModel               = hiltViewModel(),
    callVm: CallViewModel                       = hiltViewModel(),
    departmentTypeVm: DepartmentTypeViewModel   = hiltViewModel(),
    positionTypeVm: PositionTypeViewModel       = hiltViewModel(),
) {
    // 1) Charger les listes
    val allContacts     by contactVm.allForUser(userId).observeAsState(emptyList())
    val allCompanies  by companyVm.allForUser(userId).observeAsState(emptyList())
    val allApplications    by applicationVm.allForUser(userId).observeAsState(emptyList())
    val allFollowUps    by followUpVm.allForUser(userId).observeAsState(emptyList())
    val allCalls        by callVm.allForUser(userId).observeAsState(emptyList())
    val allDepartments  by departmentTypeVm.all.observeAsState(emptyList())
    val allPositions    by positionTypeVm.all.observeAsState(emptyList())

    // 2) Chercher l’existant si on édite
    val existing = contactId?.let { id -> allContacts.find { it.id == id } }

    // 3) État local pour l’entreprise liée
    var selCompanyId        by remember { mutableStateOf(existing?.companyId ?: linkedCompanyId.orEmpty()) }
    var selApplicationId    by remember { mutableStateOf(linkedApplicationId ?: existing?.applicationIds.orEmpty()) }
    var selFollowUpId       by remember { mutableStateOf(linkedFollowUpId ?: existing?.followUpIds.orEmpty()) }
    var selCallId           by remember { mutableStateOf(linkedCallId ?: existing?.callIds.orEmpty()) }
    var selectedDepartment by remember { mutableStateOf(existing?.department ?: "") }
    var selectedPosition by remember { mutableStateOf(existing?.position ?: "") }

    var showUnlinkDialog by remember { mutableStateOf(false) }
    var pendingSubmit by remember { mutableStateOf<(() -> Unit)?>(null) }


    val linkedFollowUps = allFollowUps.filter { it.id == selFollowUpId }

    var finalCompanyId = resolveCompanyId(
        existingContact = existing,
        applications = allApplications,
        followUps = linkedFollowUps,
        linkedApplicationId = selApplicationId.toString(),
        fallbackCompanyId = selCompanyId,
    )


    // 4) Les champs du formulaire
    val fields = listOf(
        FormField("firstName", "Prénom",             FieldType.TEXT,      isRequired = true),
        FormField("lastName",  "Nom",                FieldType.TEXT,      isRequired = true),
        FormField("phone",     "Téléphone",          FieldType.PHONE),
        FormField("email",     "Email",              FieldType.EMAIL),
        FormField("notes",     "Notes",              FieldType.MULTILINE_TEXT)
    )

    /*
    val updatedApplicationIds : List<String> = (existing?.applicationIds ?: emptyList()).toMutableList().apply {
        selApplicationId.let { id ->
            if (id.toString().isNotBlank() && !contains(id)) add(id.toString())
        }
    }
     */


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
            label            = "Entreprise liée",
            selectedEntityId = finalCompanyId,
            allEntities      = allCompanies,
            getEntityLabel   = { it.name },
            onEntitySelected = { finalCompanyId = it.id },
            allowCreation    = true,
            onCreateEntity   = { name ->
                // si vous voulez permettre la création inline
                val newId = UUID.randomUUID().toString()
                companyVm.save(
                    CompanyEntity(
                        id        = newId,
                        name      = name,
                        type      = null,
                        phone     = null,
                        email     = null,
                        hrEmail   = null,
                        address   = null,
                        notes     = null,
                        base      = CommonEntityFields(
                            userId    = userId,
                            syncHash  = "ent-$newId",
                            ),
                        //contactsIds = listOf(contactId!!).takeIf { contactId != null } ?: emptyList(),
                        contactsIds = listOfNotNull(contactId),
                        followUpsIds = emptyList(),
                        applicationsIds = emptyList(),
                        interviewsIds = emptyList(),
                        callsIds = emptyList(),
                        )
                )
                selCompanyId = newId
            }
        )

        // Champ “Candidature” (optionnel)
        EntitySelectorField(
            label            = "Lier à une candidature (opt.)",
            selectedEntityId = selApplicationId.toString(),
            allEntities      = allApplications.filter { it.companyId == finalCompanyId },
            getEntityLabel   = { it.title },
            onEntitySelected = { selApplicationId = it.id },
            allowCreation    = false
        )


        AutoCompleteSingleField(
            label = "Département",
            allOptions = allDepartments.map { it.name },
            selected = selectedDepartment,
            onSelected = { selectedDepartment = it },
            onNewOption = { name ->
                val newId = UUID.randomUUID().toString()
                departmentTypeVm.save(
                    DepartmentTypeEntity(
                        id = newId,
                        name = name,
                        companyId = finalCompanyId!!,
                    )
                )
                selectedDepartment = name
            }
        )

        // Sélecteur de poste
        AutoCompleteSingleField(
            label = "Poste",
            allOptions = allPositions.map { it.label },
            selected = selectedPosition,
            onSelected = { selectedPosition = it },
            onNewOption = { label ->
                val newId = UUID.randomUUID().toString()
                positionTypeVm.save(
                    PositionTypeEntity(
                        id = newId,
                        label = label,
                        base = CommonEntityFields(userId = userId, syncHash = "pos-$newId")
                    )
                )
                selectedPosition = label
            }
        )

        // Formulaire principal
        ReusableForm(
            fields = fields,
            initialValues = existing?.run {
                mapOf(
                    "firstName"  to (firstName ?: ""),
                    "lastName"   to (lastName  ?: ""),
                    "phone"      to (phone     ?: ""),
                    "email"      to (email     ?: ""),
                    "notes"      to (notes     ?: "")
                )
            } ?: emptyMap(),
            onSubmit = { form ->
                val id = existing?.id ?: UUID.randomUUID().toString()
                val hash = existing?.base?.syncHash ?: "ct-$id"
                val companyChanged = existing?.companyId != finalCompanyId

                val submitAction = {
                    val oldCompany = allCompanies.find { it.id == existing?.companyId }
                    val newCompany = allCompanies.find { it.id == finalCompanyId }

                    handleCompanyChange(
                        oldCompany = oldCompany,
                        newCompany = newCompany,
                        entityId = id,
                        companyIdField = { it.contactsIds ?: emptyList() },
                        copyWithIds = { company, newIds -> company.copy(contactsIds = newIds) },
                        save = { companyVm.save(it) }
                    )
                    // Ajouter la logique pour les autres liens si besoins

                    contactVm.save(
                        ContactEntity(
                            id = id,
                            firstName = form["firstName"] ?: "",
                            lastName = form["lastName"] ?: "",
                            phone = form["phone"],
                            email = form["email"],
                            position = selectedPosition,
                            department = selectedDepartment,
                            companyId = finalCompanyId ?: "",
                            applicationIds = (existing?.applicationIds ?: emptyList()).toMutableList().apply {
                                selApplicationId.let { if (it.toString().isNotBlank() && !contains(it)) add(it.toString()) }
                            },
                            followUpIds = (existing?.followUpIds ?: emptyList()).toMutableList().apply {
                                selFollowUpId.let { id ->
                                    if (id.toString().isNotBlank() && !contains(id)) add(id.toString())
                                }
                            },
                            interviewIds = (existing?.interviewIds ?: emptyList()).toMutableList().apply {
                                linkedInterviewId?.let { if (!contains(it)) add(it) }
                            },
                            callIds = (existing?.callIds ?: emptyList()).toMutableList().apply {
                                linkedCallId?.let { if (!contains(it)) add(it) }
                            },
                            notes = form["notes"],
                            base = existing?.base ?: CommonEntityFields(userId = userId, syncHash = hash),
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
