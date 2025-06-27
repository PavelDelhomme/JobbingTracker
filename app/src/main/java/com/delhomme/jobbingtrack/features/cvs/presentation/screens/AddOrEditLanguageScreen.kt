package com.delhomme.jobbingtrack.features.cvs.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController


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