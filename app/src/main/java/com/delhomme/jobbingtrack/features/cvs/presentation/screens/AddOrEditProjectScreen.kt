package com.delhomme.jobbingtrack.features.cvs.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.features.cvs.data.entities.ProjectEntity
import com.delhomme.jobbingtrack.features.cvs.domain.model.Project
import com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels.ProjectViewModel


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