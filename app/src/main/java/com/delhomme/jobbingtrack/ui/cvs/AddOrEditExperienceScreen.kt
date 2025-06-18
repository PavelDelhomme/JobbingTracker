package com.delhomme.jobbingtrack.ui.cvs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.cvs.models.Experience
import com.delhomme.jobbingtrack.datas.entities.cvs.ExperienceEntity
import com.delhomme.jobbingtrack.datas.viewmodels.ExperienceViewModel
import java.util.UUID


@SuppressLint("UnrememberedMutableState")
@Composable
fun AddOrEditExperienceScreen(
    navController: NavController,
    experienceVm: ExperienceViewModel,
    experienceId: String? = null,
    experiences: List<Experience>,
    onItemClick: (ExperienceEntity) -> Unit,
    onEdit: (ExperienceEntity) -> Unit,
    onArchive: (ExperienceEntity) -> Unit,
    onDelete: (ExperienceEntity) -> Unit,
    onAddClick: () -> Unit,
    userId: String,
) {
    val existing by experienceId?.let { experienceVm.byId(it).observeAsState() } ?: mutableStateOf(null)


    var title by remember { mutableStateOf(existing?.title ?: "") }
    var company by remember { mutableStateOf(existing?.company ?: "") }
    var description by remember { mutableStateOf(existing?.description ?: "") }
    var startDate by remember { mutableStateOf(existing?.startDate ?: System.currentTimeMillis()) }
    var endDate by remember { mutableStateOf(existing?.endDate ?: null) }


    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            if (experienceId == null) "Ajouter une expérience"
            else "Modifier l'expérience",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titre du poste") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = company,
            onValueChange = { company = it },
            label = { Text("Nom de l'entreprise (expérience)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = description ?: "",
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Row {
            Button(
                onClick = {

                    val now = System.currentTimeMillis()
                    val common = CommonEntityFields(
                        userId = userId,
                        syncHash = UUID.randomUUID().toString(),
                        createdAt = existing?.base?.createdAt ?: now,
                        updatedAt = now
                    )

                    val newExperience = ExperienceEntity(
                        id = experienceId ?: UUID.randomUUID().toString(),
                        title = title,
                        company = company,
                        description = description,
                        startDate = startDate,
                        endDate = endDate,
                        base = existing?.base?.copy(
                            updatedAt = now
                        ) ?: common
                    )

                    if (experienceId == null) {
                        experienceVm.save(newExperience)
                        onAddClick()
                    } else {
                        experienceVm.save(newExperience)
                        onEdit(newExperience)
                    }

                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(if (experienceId == null) "Ajouter" else "Modifier")
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Annuler")
            }
        }
    }

}