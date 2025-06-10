package com.delhomme.jobbingtrack.applications.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.applications.entities.ApplicationPlatformEntity
import com.delhomme.jobbingtrack.applications.repositories.ApplicationPlatformRepository
import com.delhomme.jobbingtrack.contacts.entities.ContactEntity
import com.delhomme.jobbingtrack.contacts.repositories.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationPlatformViewModel @Inject constructor(
    private val repo: ContactRepository
) : ViewModel() {

    fun contactsForUser(userId: String): LiveData<List<ContactEntity>> =
        repo.allForUser(userId).asLiveData()

    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: ApplicationPlatformEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: ApplicationPlatformEntity) = viewModelScope.launch { repo.delete(entity) }
}
