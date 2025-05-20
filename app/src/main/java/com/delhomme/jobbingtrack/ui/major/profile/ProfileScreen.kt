package com.delhomme.jobbingtrack.ui.major.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.data.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    profileId: String? = null,
    viewModel: ProfileViewModel = viewModel()
) {
    val profiles by viewModel.profiles.observeAsState(emptyList())
    val profile = profiles.firstOrNull { it.id == profileId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        profile?.let {
            Text("ID : ${it.id}", style = MaterialTheme.typography.titleMedium)
            Text("Sujet : ${it.subject}")
            Text("Date : ${it.dateTime}")
            Text("Notes : ${it.notes ?: "—"}")

            Spacer(Modifier.height(16.dp))
            Button(onClick = { viewModel.archive(it.id) }) {
                Text("Archiver ce profil")
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = { viewModel.delete(it.id) }, colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )) {
                Text("Supprimer définitivement")
            }
        } ?: Text("Aucun profil sélectionné", style = MaterialTheme.typography.bodyMedium)
    }
}