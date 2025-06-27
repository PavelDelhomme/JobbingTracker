package com.delhomme.jobbingtrack.features.cvs.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.features.cvs.data.entities.SkillEntity
import com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels.SkillViewModel


@Composable
fun AddOrEditSkillScreen(
    navController: NavController,
    skillVm: SkillViewModel,
    skills: List<SkillEntity>,
    onItemClick: (SkillEntity) -> Unit,
    onEdit: (SkillEntity) -> Unit,
    onArchive: (SkillEntity) -> Unit,
    onDelete: (SkillEntity) -> Unit,
    onAddClick: () -> Unit,
    userId: String,
) {

}