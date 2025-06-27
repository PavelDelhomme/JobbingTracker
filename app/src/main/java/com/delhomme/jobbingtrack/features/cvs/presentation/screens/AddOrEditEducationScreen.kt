package com.delhomme.jobbingtrack.features.cvs.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun AddOrEditEducationScreen(
    navController: NavController,
    educationVm: EducationViewModel,
    educations: List<Education>,
    onItemClick: (EducationEntity) -> Unit,
    onEdit: (EducationEntity) -> Unit,
    onArchive: (EducationEntity) -> Unit,
    onDelete: (EducationEntity) -> Unit,
    onAddClick: () -> Unit,
    userId: String,
) {

}