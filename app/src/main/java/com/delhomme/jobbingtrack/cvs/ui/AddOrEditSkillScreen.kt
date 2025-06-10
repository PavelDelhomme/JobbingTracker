package com.delhomme.jobbingtrack.cvs.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.cvs.entities.SkillEntity
import com.delhomme.jobbingtrack.cvs.models.Collaborator
import com.delhomme.jobbingtrack.cvs.viewmodels.CollaboratorViewModel
import com.delhomme.jobbingtrack.cvs.viewmodels.SkillViewModel


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