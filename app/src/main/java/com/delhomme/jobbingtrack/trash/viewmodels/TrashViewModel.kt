package com.delhomme.jobbingtrack.trash.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.delhomme.jobbingtrack.applications.entities.ApplicationEntity
import com.delhomme.jobbingtrack.applications.repositories.ApplicationRepository
import com.delhomme.jobbingtrack.companies.entities.CompanyEntity
import com.delhomme.jobbingtrack.companies.repositories.CompanyRepository
import com.delhomme.jobbingtrack.calls.entities.CallEntity
import com.delhomme.jobbingtrack.calls.repositories.CallRepository
import com.delhomme.jobbingtrack.contacts.entities.ContactEntity
import com.delhomme.jobbingtrack.contacts.repositories.ContactRepository
import com.delhomme.jobbingtrack.interviews.entities.InterviewEntity
import com.delhomme.jobbingtrack.interviews.repositories.InterviewRepository
import com.delhomme.jobbingtrack.followsup.entities.FollowUpEntity
import com.delhomme.jobbingtrack.followsup.repositories.FollowUpRepository

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