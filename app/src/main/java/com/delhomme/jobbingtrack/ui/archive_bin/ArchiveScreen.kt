package com.delhomme.jobbingtrack.ui.archive_bin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.navigation.Routes

@Composable
fun ArchiveScreen(navController: NavController) {
    val archived = FakeDataProvider.candidatures.filter { it.isArchived && !it.isDeleted }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Archives", style = MaterialTheme.typography.headlineMedium)
        archived.forEach {
            Text(
                text = "${it.title} (${it.applicationStatus})",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.CANDIDATURE_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
    }
}
