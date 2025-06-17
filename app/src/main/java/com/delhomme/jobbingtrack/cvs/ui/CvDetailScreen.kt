package com.delhomme.jobbingtrack.cvs.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.datas.viewmodels.*
import com.delhomme.jobbingtrack.navigation.Routes
import androidx.compose.runtime.getValue

@Composable
fun CvDetailScreen(
    cvId: String,
    userId: String,
    navController: NavController,
    cvViewModel: CvViewModel = viewModel(),
    experienceViewModel: ExperienceViewModel = viewModel(),
    educationViewModel: EducationViewModel = viewModel(),
    skillViewModel: SkillViewModel = viewModel(),
    projectViewModel: ProjectViewModel = viewModel(),
    languageViewModel: LanguageViewModel = viewModel(),
    collaboratorViewModel: CollaboratorViewModel = viewModel()
) {
    val cv by cvViewModel.byId(cvId).observeAsState()

    Column {
        Text("Détail du CV : ${cv?.id}", style = MaterialTheme.typography.titleLarge)

        Button(onClick = { navController.navigate(Routes.EXPERIENCE_ADD) }) {
            Text("Ajouter une expérience")
        }
        Button(onClick = { navController.navigate(Routes.EDUCATION_ADD) }) {
            Text("Ajouter une formation")
        }
        Button(onClick = { navController.navigate(Routes.SKILL_ADD) }) {
            Text("Ajouter une compétence")
        }
        Button(onClick = { navController.navigate(Routes.PROJECT_ADD) }) {
            Text("Ajouter un projet")
        }
        Button(onClick = { navController.navigate(Routes.LANGUAGE_ADD) }) {
            Text("Ajouter une langue")
        }
        Button(onClick = { navController.navigate(Routes.COLLABORATOR_ADD) }) {
            Text("Ajouter un collaborateur")
        }

        Spacer(Modifier.height(16.dp))
        Text("Contenu lié au CV à afficher ici…")
    }
}
