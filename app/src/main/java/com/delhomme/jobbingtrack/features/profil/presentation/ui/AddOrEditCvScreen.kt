package com.delhomme.jobbingtrack.features.profil.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels.CvViewModel


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