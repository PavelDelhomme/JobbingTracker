package com.delhomme.jobbingtrack.ui.cvs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController


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