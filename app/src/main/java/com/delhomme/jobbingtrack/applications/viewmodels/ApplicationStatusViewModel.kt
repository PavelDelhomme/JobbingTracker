package com.delhomme.jobbingtrack.applications.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.applications.entities.ApplicationStatusEntity
import com.delhomme.jobbingtrack.applications.repositories.ApplicationRepository
import com.delhomme.jobbingtrack.applications.repositories.ApplicationStatusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationStatusViewModel @Inject constructor(
    private val repo: ApplicationRepository
) : ViewModel()
{
    private val dao = JobbingTrackApp.database.applicationStatusDao()

    fun applicationsForUser(userId: String): LiveData<List<ApplicationStatusEntity>> = repo.allForUser(userId).asLiveData()
    fun byId(id: String): LiveData<ApplicationStatusEntity?> = repo.byId(id).asLiveData()

    fun save(status: ApplicationStatusEntity) = viewModelScope.launch { repo.save(status) }
    fun delete(status: ApplicationStatusEntity) = viewModelScope.launch { repo.delete(status) }
}