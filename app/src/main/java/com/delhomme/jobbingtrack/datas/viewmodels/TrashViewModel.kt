package com.delhomme.jobbingtrack.datas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationEntity
import com.delhomme.jobbingtrack.datas.entities.calls.CallEntity
import com.delhomme.jobbingtrack.datas.entities.companies.CompanyEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewEntity
import com.delhomme.jobbingtrack.datas.repositories.ApplicationRepository
import com.delhomme.jobbingtrack.datas.repositories.CallRepository
import com.delhomme.jobbingtrack.datas.repositories.CompanyRepository
import com.delhomme.jobbingtrack.datas.repositories.ContactRepository
import com.delhomme.jobbingtrack.datas.repositories.FollowUpRepository
import com.delhomme.jobbingtrack.datas.repositories.InterviewRepository


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