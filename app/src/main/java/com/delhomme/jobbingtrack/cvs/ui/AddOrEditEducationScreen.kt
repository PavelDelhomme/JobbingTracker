package com.delhomme.jobbingtrack.cvs.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.cvs.models.Collaborator
import com.delhomme.jobbingtrack.cvs.models.Education
import com.delhomme.jobbingtrack.datas.entities.cvs.EducationEntity
import com.delhomme.jobbingtrack.datas.viewmodels.EducationViewModel

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