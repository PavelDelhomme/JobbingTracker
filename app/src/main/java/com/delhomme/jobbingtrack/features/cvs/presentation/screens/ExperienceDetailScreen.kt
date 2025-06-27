package com.delhomme.jobbingtrack.features.cvs.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels.ExperienceViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ExperienceDetailScreen(
    experienceId: String,
    navController: NavController,
    viewModel: ExperienceViewModel = viewModel()
) {
    val experience by viewModel.byId(experienceId).observeAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Détail de l'expérience", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(12.dp))
        Text("Poste : ${experience?.title ?: "Non défini"}")
        Text("Entreprise : ${experience?.company ?: "Non définie"}")
        Text("Description : ${experience?.description ?: "Non définie"}")
        Text("Début : ${experience?.startDate?.let { formatDate(it) } ?: "Non défini"}")
        Text("Fin : ${experience?.endDate?.let { formatDate(it) } ?: "En cours"}")
    }
}

fun formatDate(timestamp: Long): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        .format(Date(timestamp))
}