package com.delhomme.jobbingtrack.features.profil.presentation.ui

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
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.features.cvs.data.entities.CollaboratorEntity
import com.delhomme.jobbingtrack.features.cvs.domain.model.Collaborator
import com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels.CollaboratorViewModel
import java.util.UUID


@SuppressLint("UnrememberedMutableState")
@Composable
fun AddOrEditCollaboratorScreen(
    navController: NavController,
    collaboratorVm: CollaboratorViewModel,
    collaboratorId: String? = null,
    collaborators: List<Collaborator>,
    onItemClick: (CollaboratorEntity) -> Unit,
    onEdit: (CollaboratorEntity) -> Unit,
    onArchive: (CollaboratorEntity) -> Unit,
    onDelete: (CollaboratorEntity) -> Unit,
    onAddClick: () -> Unit,
    userId: String,
) {
    val existing by collaboratorId?.let { collaboratorVm.byId(it).observeAsState() } ?: mutableStateOf(null)

    var name by remember { mutableStateOf(existing?.name ?: "") }
    var role by remember { mutableStateOf(existing?.role ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            if (collaboratorId == null) "Ajouter un collaborateur"
            else "Modifier le collaborateur",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nom") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = role,
            onValueChange = { role = it },
            label = { Text("Rôle") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = {
                    val now = System.currentTimeMillis()
                    val newCollaborator = CollaboratorEntity(
                        id = collaboratorId ?: UUID.randomUUID().toString(),
                        name = name,
                        role = role,
                        base = existing?.base?.copy(updated_at = now) ?: CommonEntityFields(
                            userId = userId,
                            syncHash = UUID.randomUUID().toString(),
                            created_at = now,
                            updated_at = now
                        )
                    )

                    if (collaboratorId == null) {
                        collaboratorVm.save(newCollaborator)
                        onAddClick()
                    } else {
                        collaboratorVm.save(newCollaborator)
                        onEdit(newCollaborator)
                    }

                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(if (collaboratorId == null) "Ajouter" else "Modifier")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Annuler")
            }
        }
    }
}
