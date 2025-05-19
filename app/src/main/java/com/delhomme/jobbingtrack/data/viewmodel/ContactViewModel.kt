package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import com.delhomme.jobbingtrack.data.local.repository.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ContactRepository(JobbingTrackApp.database.contactDao())

    val contacts: LiveData<List<ContactEntity>> = repo.contacts.asLiveData()

    fun save(contact: ContactEntity) = viewModelScope.launch {
        repo.save(contact)
    }
    fun archive(id: String) = viewModelScope.launch {
        repo.archive(id)
    }
    fun delete(id: String) = viewModelScope.launch {
        repo.delete(id)
    }
}