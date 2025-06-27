package com.delhomme.jobbingtrack.feature.profil.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController


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