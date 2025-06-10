package com.delhomme.jobbingtrack.cvs.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.api.CV
import com.delhomme.jobbingtrack.applications.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.contacts.viewmodels.ContactViewModel
import com.delhomme.jobbingtrack.cvs.entities.CVEntity
import com.delhomme.jobbingtrack.cvs.models.Collaborator
import com.delhomme.jobbingtrack.cvs.viewmodels.CollaboratorViewModel
import com.delhomme.jobbingtrack.cvs.viewmodels.CvViewModel


@Composable
fun AddOrEditCvScreen(
    navController: NavController,
    cvVm: CvViewModel,
    cvs: List<CV>,
    cvId: String? = null,
    onItemClick: (CVEntity) -> Unit,
    onEdit: (CVEntity) -> Unit,
    onArchive: (CVEntity) -> Unit,
    onDelete: (CVEntity) -> Unit,
    onAddClick: () -> Unit,
    userId: String,
    ) {

}