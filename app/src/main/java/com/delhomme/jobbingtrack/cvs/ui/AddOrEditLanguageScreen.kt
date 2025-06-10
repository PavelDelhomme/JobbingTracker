package com.delhomme.jobbingtrack.cvs.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.cvs.entities.LanguageEntity
import com.delhomme.jobbingtrack.cvs.models.Collaborator
import com.delhomme.jobbingtrack.cvs.models.Language
import com.delhomme.jobbingtrack.cvs.viewmodels.CollaboratorViewModel
import com.delhomme.jobbingtrack.cvs.viewmodels.LanguageViewModel


@Composable
fun AddOrEditLanguageScreen(
    navController: NavController,
    languageVm: LanguageViewModel,
    languages: List<Language>,
    onItemClick: (LanguageEntity) -> Unit,
    onEdit: (LanguageEntity) -> Unit,
    onArchive: (LanguageEntity) -> Unit,
    onDelete: (LanguageEntity) -> Unit,
    onAddClick: () -> Unit,
    userId: String,

    ) {

}