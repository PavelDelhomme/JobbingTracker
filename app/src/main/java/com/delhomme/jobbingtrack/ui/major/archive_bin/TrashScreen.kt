package com.delhomme.jobbingtrack.ui.major.archive_bin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.viewmodel.AppelViewModel
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntrepriseViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntretienViewModel
import com.delhomme.jobbingtrack.data.viewmodel.RelanceViewModel
import com.delhomme.jobbingtrack.navigation.Routes

@Composable
fun TrashScreen(
    navController: NavController,
    userId: String,
    candidatureViewModel: CandidatureViewModel,
    entrepriseViewModel: EntrepriseViewModel,
    appelViewModel: AppelViewModel,
    contactViewModel: ContactViewModel,
    entretienViewModel: EntretienViewModel,
    relanceViewModel: RelanceViewModel,
    onRestore: () -> Unit,
    onDelete: () -> Unit
) {

    val candidatures = candidatureViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.isDeleted }

    val entreprises = entrepriseViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.isDeleted }

    val appels = appelViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.isDeleted }

    val contacts = contactViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.isDeleted }

    val entretiens = entretienViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.entretien.isDeleted }

    val relances = relanceViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.isDeleted }

    val deleted = candidatures + entreprises + appels + contacts + entretiens + relances

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Corbeille", style = MaterialTheme.typography.headlineMedium)
        candidatures.forEach {
            Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(it.title)
                Row {
                    TextButton(onClick = {
                        candidatureViewModel.restore(listOf(it.id), userId)
                        onRestore()
                    }) { Text("Restaurer") }
                    TextButton(onClick = {
                        candidatureViewModel.deleteForever(listOf(it.id), userId)
                        onDelete()
                    }) { Text("Supprimer") }
                }
            }
        }
    }
}