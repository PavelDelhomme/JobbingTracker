package com.delhomme.jobbingtrack.ui.cvs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.cvs.models.Language
import com.delhomme.jobbingtrack.datas.entities.cvs.LanguageEntity
import com.delhomme.jobbingtrack.datas.viewmodels.LanguageViewModel


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