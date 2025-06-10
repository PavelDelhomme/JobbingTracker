package com.delhomme.jobbingtrack.cvs.ui

import androidx.navigation.NavController
import com.delhomme.jobbingtrack.applications.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.calls.viewmodels.CallViewModel
import com.delhomme.jobbingtrack.companies.viewmodels.CompanyViewModel
import com.delhomme.jobbingtrack.contacts.viewmodels.ContactViewModel
import com.delhomme.jobbingtrack.followsup.viewmodels.FollowUpViewModel
import com.delhomme.jobbingtrack.interviews.viewmodels.InterviewViewModel

class CVUploadScreen(
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

}