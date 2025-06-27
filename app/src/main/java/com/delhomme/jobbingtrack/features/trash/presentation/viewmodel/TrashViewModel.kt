package com.delhomme.jobbingtrack.features.trash.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.application.data.repositories.ApplicationRepository
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.call.data.repositories.CallRepository
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.company.data.repositories.CompanyRepository
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.contact.data.repositories.ContactRepository
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.followup.data.repositories.FollowUpRepository
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity
import com.delhomme.jobbingtrack.features.interview.data.repositories.InterviewRepository


class TrashViewModel(
    private val applicationRepository: ApplicationRepository,
    private val companyRepository: CompanyRepository,
    private val callRepository: CallRepository,
    private val contactRepository: ContactRepository,
    private val interviewRepository: InterviewRepository,
    private val followUpRepository: FollowUpRepository
) : ViewModel() {

    fun getDeletedApplications(userId: String): LiveData<List<ApplicationEntity>> =
        applicationRepository.deletedForUser(userId).asLiveData()

    fun getDeletedCompanies(userId: String): LiveData<List<CompanyEntity>> =
        companyRepository.deletedForUser(userId).asLiveData()

    fun getDeletedCalls(userId: String): LiveData<List<CallEntity>> =
        callRepository.deletedForUser(userId).asLiveData()

    fun getDeletedContacts(userId: String): LiveData<List<ContactEntity>> =
        contactRepository.deletedForUser(userId).asLiveData()

    fun getDeletedInterviews(userId: String): LiveData<List<InterviewEntity>> =
        interviewRepository.deletedForUser(userId).asLiveData()

    fun getDeletedFollowUps(userId: String): LiveData<List<FollowUpEntity>> =
        followUpRepository.deletedForUser(userId).asLiveData()
}