package com.delhomme.jobbingtrack.features.cvs.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.features.cvs.data.entities.EducationEntity
import com.delhomme.jobbingtrack.features.cvs.domain.model.Education
import com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels.EducationViewModel


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