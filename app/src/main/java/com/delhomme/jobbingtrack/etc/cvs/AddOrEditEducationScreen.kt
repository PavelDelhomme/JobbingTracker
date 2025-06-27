package com.delhomme.jobbingtrack.etc.cvs

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