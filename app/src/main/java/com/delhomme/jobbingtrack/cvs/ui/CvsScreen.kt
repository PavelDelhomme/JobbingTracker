package com.delhomme.jobbingtrack.cvs.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.api.CV
import com.delhomme.jobbingtrack.applications.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.calls.viewmodels.CallViewModel
import com.delhomme.jobbingtrack.companies.viewmodels.CompanyViewModel
import com.delhomme.jobbingtrack.contacts.viewmodels.ContactViewModel
import com.delhomme.jobbingtrack.cvs.entities.CVEntity
import com.delhomme.jobbingtrack.cvs.viewmodels.CvViewModel
import com.delhomme.jobbingtrack.followsup.viewmodels.FollowUpViewModel
import com.delhomme.jobbingtrack.interviews.viewmodels.InterviewViewModel


@Composable
fun CvsScreen(
    navController: NavController,
    cvVm: CvViewModel,
    cvs: List<CVEntity>,
    onItemClick: (CVEntity) -> Unit,
    onEdit: (CVEntity) -> Unit,
    onArchive: (CVEntity) -> Unit,
    onDelete: (CVEntity) -> Unit,
    onAddClick: () -> Unit,
    userId: String,
) {


}