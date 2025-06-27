package com.delhomme.jobbingtrack.etc.cvs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.datas.viewmodels.CollaboratorViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.CvViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.EducationViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.ExperienceViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.LanguageViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.ProjectViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.SkillViewModel
import com.delhomme.jobbingtrack.navigation.Routes


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
