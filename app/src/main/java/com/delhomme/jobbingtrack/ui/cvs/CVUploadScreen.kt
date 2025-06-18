package com.delhomme.jobbingtrack.ui.cvs

import androidx.navigation.NavController
import com.delhomme.jobbingtrack.datas.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.CallViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.CompanyViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.ContactViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.FollowUpViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.InterviewViewModel


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