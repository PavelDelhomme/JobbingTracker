package com.delhomme.jobbingtrack.features.cvs.presentation.screens

import androidx.navigation.NavController
import com.delhomme.jobbingtrack.features.application.presentation.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.features.call.presentation.viewmodel.CallViewModel
import com.delhomme.jobbingtrack.features.company.presentation.viewmodel.CompanyViewModel
import com.delhomme.jobbingtrack.features.contact.presentation.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.features.followup.presentation.viewmodel.FollowUpViewModel
import com.delhomme.jobbingtrack.features.interview.presentation.viewmodel.InterviewViewModel


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