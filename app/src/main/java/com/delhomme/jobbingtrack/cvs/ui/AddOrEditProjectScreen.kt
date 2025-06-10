package com.delhomme.jobbingtrack.cvs.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.cvs.entities.ProjectEntity
import com.delhomme.jobbingtrack.cvs.models.Collaborator
import com.delhomme.jobbingtrack.cvs.models.Project
import com.delhomme.jobbingtrack.cvs.viewmodels.CollaboratorViewModel
import com.delhomme.jobbingtrack.cvs.viewmodels.ProjectViewModel


@Composable
fun AddOrEditProjectScreen(
    navController: NavController,
    projectVm: ProjectViewModel,
    projects: List<Project>,
    onItemClick: (ProjectEntity) -> Unit,
    onEdit: (ProjectEntity) -> Unit,
    onArchive: (ProjectEntity) -> Unit,
    onDelete: (ProjectEntity) -> Unit,
    onAddClick: () -> Unit,
    userId: String,

    ) {

}