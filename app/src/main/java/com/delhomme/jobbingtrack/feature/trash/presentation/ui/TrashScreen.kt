package com.delhomme.jobbingtrack.feature.trash.presentation.ui

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


@Composable
fun TrashScreen(
    navController: NavController,
    userId: String,
    applicationViewModel: ApplicationViewModel,
    companyViewModel: CompanyViewModel,
    callViewModel: CallViewModel,
    contactViewModel: ContactViewModel,
    interviewViewModel: InterviewViewModel,
    followUpViewModel: FollowUpViewModel,
    onRestore: () -> Unit,
    onDelete: () -> Unit
) {

    val applications = applicationViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.base.isDeleted }

    val companies = companyViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.base.isDeleted }

    val calls = callViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.base.isDeleted }

    val contacts = contactViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.base.isDeleted }

    val interviews = interviewViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.interview.base.isDeleted }

    val followsUps = followUpViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.base.isDeleted }

    val deleteds = applications + companies + calls + contacts + interviews + followsUps

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Corbeille", style = MaterialTheme.typography.headlineMedium)
        applications.forEach {
            Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(it.title)
                Row {
                    TextButton(onClick = {
                        applicationViewModel.restore(listOf(it.id), userId)
                        onRestore()
                    }) { Text("Restaurer") }
                    TextButton(onClick = {
                        applicationViewModel.deleteForever(listOf(it.id), userId)
                        onDelete()
                    }) { Text("Supprimer") }
                }
            }
        }
    }
}



@Composable
fun TrashScreen(
    navController: NavController,
    userId: String,
    applicationViewModel: ApplicationViewModel,
    companyViewModel: CompanyViewModel,
    callViewModel: CallViewModel,
    contactViewModel: ContactViewModel,
    interviewViewModel: InterviewViewModel,
    followUpViewModel: FollowUpViewModel,
    onRestore: () -> Unit,
    onDelete: () -> Unit
) {

    val applications = applicationViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.base.isDeleted }

    val companies = companyViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.base.isDeleted }

    val calls = callViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.base.isDeleted }

    val contacts = contactViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.base.isDeleted }

    val interviews = interviewViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.interview.base.isDeleted }

    val followsUps = followUpViewModel
        .allForUser(userId).observeAsState(emptyList()).value
        .filter { it.base.isDeleted }

    val deleteds = applications + companies + calls + contacts + interviews + followsUps

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Corbeille", style = MaterialTheme.typography.headlineMedium)
        applications.forEach {
            Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(it.title)
                Row {
                    TextButton(onClick = {
                        applicationViewModel.restore(listOf(it.id), userId)
                        onRestore()
                    }) { Text("Restaurer") }
                    TextButton(onClick = {
                        applicationViewModel.deleteForever(listOf(it.id), userId)
                        onDelete()
                    }) { Text("Supprimer") }
                }
            }
        }
    }
}