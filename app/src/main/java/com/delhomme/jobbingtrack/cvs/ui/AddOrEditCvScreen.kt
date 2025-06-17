package com.delhomme.jobbingtrack.cvs.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.api.CV
import com.delhomme.jobbingtrack.datas.entities.cvs.CVEntity
import com.delhomme.jobbingtrack.datas.viewmodels.CvViewModel


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