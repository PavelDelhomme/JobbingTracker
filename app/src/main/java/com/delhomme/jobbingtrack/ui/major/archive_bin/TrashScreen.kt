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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.navigation.Routes


@Composable
fun TrashScreen(navController: NavController) {
    val deleted = FakeDataProvider.candidatures.filter { it.isDeleted }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Corbeille", style = MaterialTheme.typography.headlineMedium)
        deleted.forEach {
            Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(it.title)
                Row {
                    TextButton(onClick = {
                        it.isDeleted = false
                        it.isArchived = false
                    }) { Text("Restaurer") }
                    TextButton(onClick = {
                        FakeDataProvider.candidatures.remove(it)
                    }) { Text("Supprimer") }
                }
            }
        }
    }
}
