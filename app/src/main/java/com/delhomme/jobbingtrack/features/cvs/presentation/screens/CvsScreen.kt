package com.delhomme.jobbingtrack.features.cvs.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels.CvViewModel


@Composable
fun CvsScreen(
    navController: NavController,
    cvVm: CvViewModel,
    cvs: List<CVEntity>,
    onItemClick: (CVEntity) -> Unit,
    onEdit: (CVEntity) -> Unit,
    onArchive: (CVEntity) -> Unit,
    onDelete: (CVEntity) -> Unit,
    onAddClick: () -> Unit,
    userId: String,
) {


}